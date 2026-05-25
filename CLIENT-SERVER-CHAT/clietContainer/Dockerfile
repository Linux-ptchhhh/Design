# --- Stage 1: Build & Compile dependencies ---
FROM python:3.11-slim AS builder
WORKDIR /app

# Install system utilities required for building confluent-kafka
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential && \
    rm -rf /var/lib/apt/lists/*

# Install client requirements directly
RUN pip install --user --no-cache-dir websockets confluent-kafka

# --- Stage 2: Final Lightweight Runtime ---
FROM python:3.11-slim
WORKDIR /app

# Copy the isolated compiled dependencies over
COPY --from=builder /root/.local /root/.local
COPY client.py .

# Map path variables so python can resolve our dependencies
ENV PATH=/root/.local/bin:$PATH

CMD ["python", "client.py"]
