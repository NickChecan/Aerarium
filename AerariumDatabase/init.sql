-- Create a Aerarium database instance if not exists
CREATE DATABASE aerarium;
SELECT 'CREATE DATABASE aerarium'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'aerarium')\gexec

\connect aerarium
