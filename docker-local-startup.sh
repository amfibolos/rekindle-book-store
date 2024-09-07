#!/bin/bash

compose_files="-f ./docker-compose/rekindle-local/docker-compose.infra.yml -f ./docker-compose/rekindle-local/docker-compose.database.yml -f ./docker-compose/rekindle-local/docker-compose.backend.yml"

docker_compose_cmd="docker-compose $compose_files --profile all up -d --wait"
echo "Running command: $docker_compose_cmd"
eval "$docker_compose_cmd"