# --- Stage 1: Build & Compile dependencies ---
FROM python:3.11-slim AS builder
WORKDIR /app

# Install system compiler utilities required for building confluent-kafka
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential && \
    rm -rf /var/lib/apt/lists/*

COPY requirements.txt .
RUN pip install --user --no-cache-dir -r requirements.txt

# --- Stage 2: Final Lightweight Runtime ---
FROM python:3.11-slim
WORKDIR /app

# Copy the isolated workspace dependencies from the builder stage
COPY --from=builder /root/.local /root/.local
COPY server.py .

# Document the container's standard operational port
EXPOSE 8765

# Ensure system runtime paths map out dependencies correctly
ENV PATH=/root/.local/bin:$PATH

CMD ["python", "server.py"]
