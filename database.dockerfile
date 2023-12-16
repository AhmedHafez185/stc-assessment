# Use an official PostgreSQL image as a base image
FROM postgres:latest

# Environment variables for configuring PostgreSQL
ENV POSTGRES_DB file_system
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD admin

# Custom initialization scripts
COPY init.sql /docker-entrypoint-initdb.d/

# Expose PostgreSQL port
EXPOSE 5432