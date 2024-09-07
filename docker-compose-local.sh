#!/bin/bash

profile_list=
mode_params=""
wait_flag=""
detached=""
profiles=""
compose_files="-f ./docker-compose/rekindle-local/docker-compose.infra.yml -f ./docker-compose/rekindle-local/docker-compose.database.yml -f ./docker-compose/rekindle-local/docker-compose.backend.yml"

usage(){
  echo "Choose deployment profiles [-p "\""profile1 profile2 profile3"\""], startup mode [-m up or -m start etc] and optional detached mode -d and wait flag -w"
}

while getopts ":p:m:wd" opt; do
  case $opt in
  p)
    profile_list=$OPTARG
    ;;
  m)
    mode_params=$OPTARG
    ;;
  d)
    detached="-d"
    ;;
  w)
    wait_flag="--wait"
    ;;
  \?)
    echo "Invalid option: -$OPTARG" >&2
    usage
    ;;
  :)
    echo "Option: -$OPTARG requires an argument" >&2
    usage
    ;;
  esac
done

if [ -z "$profile_list" ]; then
  echo "The -p option is required"
  usage
fi

for string in $profile_list; do
  profiles+="--profile $string"
  profiles+=" "
done

docker_compose_cmd="docker-compose $compose_files $profiles $mode_params $detached $wait_flag"
echo "Running command: $docker_compose_cmd"
eval "$docker_compose_cmd"