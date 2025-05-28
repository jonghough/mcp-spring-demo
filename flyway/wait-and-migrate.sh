#!/bin/sh
set -e

echo "Waiting for Postgres to be ready at $FLYWAY_URL..."


until /flyway/flyway info > /dev/null 2>&1; do
  echo "Postgres is unavailable - sleeping"
  sleep 2
done

echo "Postgres is up - running migration"
/flyway/flyway migrate