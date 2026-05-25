import asyncio
import websockets
import cmd
from confluent_kafka import Producer
import json
import os

KAFKA_BROKER = os.getenv('KAFKA_BROKER', 'host.docker.internal:9092')

config = {
    'bootstrap.servers': KAFKA_BROKER,
    'client.id': 'my-first-python-producer'
}
producer = Producer(config)


def delivery_report(err, msg):
    if err is not None:
        print(f"\n[KAFKA ERROR] Message delivery failed: {err}")
    else:
        pass

class TerminalClient(cmd.Cmd):
    # This is the shell format prompt that will constantly appear
    prompt = "chat-cli> "
    intro = "Type 'send <message>' to chat, or 'quit' to exit."

    def __init__(self, loop, websocket):
        super().__init__()
        self.loop = loop
        self.websocket = websocket

    def do_send(self, arg):
        """Send a message to the server."""
        if not arg:
            print("Please provide a message. Example: send hello")
            return
        # Safely send the message using the async loop from this thread
        asyncio.run_coroutine_threadsafe(self.websocket.send(arg), self.loop)

    def do_quit(self, arg):
        client_ip, client_port = self.websocket.local_address
        data = {"ip": client_ip, "status": "OFFLINE"}
        raw_bytes = json.dumps(data).encode('utf-8')

        # Fix 4: Matched topic to 'user-presence'
        producer.produce(topic='user-presence', value=raw_bytes, callback=delivery_report)
        producer.poll(0)
        return True

    def emptyline(self):
        """Prevents repeating the last command if you just press Enter."""
        pass

async def receive_messages(websocket, terminal):

    try:
        async for message in websocket:
            print(f"\r[Peer]: {message}")
            print(terminal.prompt, end="", flush=True)
    except websockets.exceptions.ConnectionClosed:
        print("\r\n[DISCONNECTED] Server closed the connection.")
        print(terminal.prompt, end="", flush=True)

def start_terminal(terminal):
    """Wrapper to start the blocking cmd loop."""
    terminal.cmdloop()

async def main():
    SERVER_IP = os.getenv('SERVER_IP', 'host.docker.internal')
    uri = f"ws://{SERVER_IP}:8765"

    try:
        async with websockets.connect(uri) as websocket:
            print(f"Connected to {uri}!\n")

            # Get the current event loop to pass into the terminal
            loop = asyncio.get_running_loop()
            terminal = TerminalClient(loop, websocket)

            # Start the background listener task
            listen_task = asyncio.create_task(receive_messages(websocket, terminal))

            # Run the cmd terminal in a background thread (replacing send_messages)
            await asyncio.to_thread(start_terminal, terminal)

            # Cleanup when terminal exits
            listen_task.cancel()

    except ConnectionRefusedError:
        print(f"Could not connect to server at {uri}. Is it running?")

if __name__ == "__main__":
    try:
        asyncio.run(main())
    except KeyboardInterrupt:
        print("\n[STOPPING] Client manually shut down.")
    finally:
        # Ensure all Kafka messages are sent before shutting down
        producer.flush()
