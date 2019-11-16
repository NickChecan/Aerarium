-- Create a Aerarium database instance if not exists
SELECT 'CREATE DATABASE aerarium'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'aerarium')\gexec

-- Create a database for the Sonarqube tool
SELECT 'CREATE DATABASE sonar'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'sonar')\gexec

\connect aerarium
