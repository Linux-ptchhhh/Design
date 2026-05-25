import asyncio
import websockets
import aiohttp
from confluent_kafka import Producer
import json
import os

connected_clients = set()

KAFKA_BROKER = os.getenv('KAFKA_BROKER', 'localhost:9092')
SPRING_BOOT_URL = os.getenv('SPRING_BOOT_URL', 'http://localhost:8080/api/data/print')

config = {
    'bootstrap.servers': KAFKA_BROKER,
    'client.id': 'my-first-python-producer'
}

producer = Producer(config)

# Fix 1: Added the missing delivery_report function
def delivery_report(err, msg):
    if err is not None:
        print(f"[KAFKA ERROR] Message delivery failed: {err}")
    else:
        print(f"[KAFKA SUCCESS] Message delivered to {msg.topic()} [{msg.partition()}]")

async def javaDatabasecall(message, ip):
    url = SPRING_BOOT_URL
    data = {"client": ip}

    # Send ONLINE status
    presence = {"ip" : ip, "status" : "ONLINE"}

    try:
        async with aiohttp.ClientSession() as session:
            async with session.post(url, json=data) as response:
                raw_bytes = json.dumps(presence).encode('utf-8')

                # Fix 2: Changed topic to 'user-presence' to match your Spring Boot app
                producer.produce(topic='user-presence', value=raw_bytes, callback=delivery_report)

                # Use poll(0) instead of flush() inside the async loop so it doesn't block the server
                producer.poll(0)

    except aiohttp.ClientConnectorError:
        print("[JAVA BACKEND] Error: Could not connect to Spring Boot. Is it running?")
    except Exception as e:
        print(f"[JAVA BACKEND] Unexpected error: {e}")

async def handle_client(websocket):
    connected_clients.add(websocket)
    client_ip, client_port = websocket.remote_address # Moved this up to use in the finally block

    print(f"[NEW CONNECTION] Client {client_ip} connected. Total active: {len(connected_clients)}")

    try:
        async for message in websocket:
            print(f"[RECEIVED from {client_ip}] {message}")

            asyncio.create_task(javaDatabasecall(message, client_ip))

            peers = connected_clients - {websocket}
            if peers:
                websockets.broadcast(peers, message)

    except websockets.exceptions.ConnectionClosed:
        print("[DISCONNECTED] Client closed the connection unexpectedly.")
    except Exception as e:
        print(f"[ERROR] WebSocket encountered an error: {e}")
    finally:
        connected_clients.remove(websocket)
        print(f"[DISCONNECTED] Client {client_ip} left. Total active: {len(connected_clients)}")

        # Fix 3: Send the OFFLINE status to Kafka so Java deletes the IP from the DB
        offline_presence = {"ip": client_ip, "status": "OFFLINE"}
        producer.produce(topic='user-presence', value=json.dumps(offline_presence).encode('utf-8'), callback=delivery_report)
        producer.poll(0)

async def main():
    async with websockets.serve(handle_client, "0.0.0.0", 8765):
        print("[STARTING] WebSocket Server running on ws://0.0.0.0:8765...")
        await asyncio.Future()

if __name__ == "__main__":
    # Fix 4: Removed the stray 'p' that was here
    try:
        asyncio.run(main())
    except KeyboardInterrupt:
        print("\n[STOPPING] Server manually shut down.")
        # Ensure all Kafka messages are sent before shutting down
        producer.flush()
