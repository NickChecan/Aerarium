-- Create a Aerarium database instance if not exists
SELECT 'CREATE DATABASE aerarium'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'aerarium')\gexec

\connect aerarium
