#!/bin/bash

host=$1
shift
port=$1
shift
cmd="$@"

retries=30  # số lần thử tối đa
count=0

until nc -z -v -w5 $host $port; do
  echo "[$count/$retries] Waiting for $host:$port..."
  count=$((count + 1))
  if [ $count -ge $retries ]; then
    echo "❌ Failed to connect to $host:$port after $retries attempts."
    exit 1
  fi
  sleep 1
done

echo "✅ $host:$port is up. Executing command..."
exec $cmd
