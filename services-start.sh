#!/bin/bash
usage="For kafka in docker connection choose [-d], for local kafka connection choose [-l]"
kafka_opts=''
has_l=0
has_d=0
while getopts 'dlh' increment;
do
  case "${increment}" in
    d) has_d=1 ;;
    l) has_l=1 ;;
    h) echo "$usage"
       exit 1 ;;
    *) echo "$usage" >&2
       exit 1 ;;
  esac
done
if ((has_l + has_d > 1)); then
    echo "either [-d] or [-l] flag is allowed. Select [-h] for help" >&2
    exit 1
elif ((has_l + has_d == 0)); then
    echo "either [-d] or [-l] flag must be chosen. Select [-h] for help" >&2
    exit 1
fi
if ((has_d)); then
    kafka_opts="kafka-docker"
elif ((has_l)); then
    kafka_opts="kafka"
fi

ROOTDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
EUREKA_SERVER_DIR="$ROOTDIR/infrastructure/eureka-server/build/lib/eureka-server.jar"
CONFIG_SERVER_DIR="$ROOTDIR/infrastructure/config-server/build/lib/config-server.jar"
GATEWAY_SERVER_DIR="$ROOTDIR/infrastructure/gateway-server/build/lib/gateway-server.jar"
AUTH_SERVER_DIR="$ROOTDIR/infrastructure/authorization-server/build/lib/authorization-server.jar"
BOOK_SERVER_DIR="$ROOTDIR/bookstore-service/bookstore-microservice/build/lib/bookstore-microservice.jar"
CUSTOMER_SERVER_DIR="$ROOTDIR/customer-service/customer-microservice/build/lib/customer-microservice.jar"
ORDER_SERVER_DIR="$ROOTDIR/order-service/order-microservice/build/lib/order-microservice.jar"
PAYMENT_SERVER_DIR="$ROOTDIR/payment-service/payment-microservice/build/lib/payment-microservice.jar"

# Add default JVM options here.
DEFAULT_JVM_OPTS=""

# Find java
if [ -n "$JAVA_HOME" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
else
  JAVA_EXE="java"
  command -v "$JAVA_EXE" >/dev/null 2>&1 || { echo >&2 "ERROR: JAVA_HOME is not set, and 'java' command not found."; exit 1; }
fi

# Health check function
healthCheck() {
    local url="http://localhost:$1/actuator/health/readiness"
    local retries=5
    local service="$2"

    for ((i=1; i<=$retries; i++)); do
        if curl -f -s "$url" | grep -q '{"status":"UP"}'; then
            echo "Service $service is UP"
            return 0
        else
            echo "Waiting for $service service to be UP (Attempt $i of $retries)"
            sleep 10
        fi
    done

    echo "Service is not UP after $retries attempts"
    echo "Health check for $service service failed. Exiting script & killing remaining processes"
    kill_processes "eureka-server.jar"
    kill_processes "config-server.jar"
    kill_processes "authorization-server.jar"
    kill_processes "gateway-server.jar"
    kill_processes "customer-microservice.jar"
    kill_processes "bookstore-microservice.jar"
    kill_processes "order-microservice.jar"
    kill_processes "payment-microservice.jar"
    exit 1
}

kill_processes() {
    OS=$(uname -s)
    local process_name="$1"

    case "$OS" in
        Linux* | Darwin*)
            # For Linux and macOS
            pids=$(pgrep -f "$process_name")
            if [ -n "$pids" ]; then
                echo "Killing processes with commandline containing '$process_name'"
                kill -9 $pids
            else
                echo "No processes found with commandline containing '$process_name'"
            fi
            ;;
        CYGWIN* | MINGW32* | MINGW64* | MSYS*)
            # For Windows (Cygwin, Git Bash, MSYS, etc.)
            pid=$(jps | grep "$process_name" | cut -d ' ' -f 1)
            if [ -n "$pid" ]; then
                echo "Killing processes with commandline containing '$process_name'"
                taskkill //F //PID "$pid"
            else
                echo "No processes found with commandline containing '$process_name'"
            fi
            ;;
        *)
            echo "Unsupported operating system: $OS"
            ;;
    esac
}

# Execute servers and services

# Starting Eureka Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -jar "$EUREKA_SERVER_DIR" > /dev/null 2>&1 &
healthCheck 8761 "Eureka"

# Starting Config Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -jar "$CONFIG_SERVER_DIR" > /dev/null 2>&1 &
healthCheck 8021 "Config"

# Starting Gateway Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka -jar "$GATEWAY_SERVER_DIR" > /dev/null 2>&1 &

# Starting Authorization Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka -jar "$AUTH_SERVER_DIR" > /dev/null 2>&1 &

# Starting Customer Service
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka,postgre -jar "$CUSTOMER_SERVER_DIR" > /dev/null 2>&1 &

# Starting Bookstore Service
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka,postgre,$kafka_opts -jar "$BOOK_SERVER_DIR" > /dev/null 2>&1 &

# Starting Order Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka,postgre,$kafka_opts -jar "$ORDER_SERVER_DIR" > /dev/null 2>&1 &

# Starting Payment Server
nohup "$JAVA_EXE" $DEFAULT_JVM_OPTS -Dspring.profiles.active=eureka,postgre,$kafka_opts -jar "$PAYMENT_SERVER_DIR" > /dev/null 2>&1 &

healthCheck 8181 "Order"
healthCheck 8182 "Payment"
healthCheck 8183 "Bookstore"
healthCheck 8184 "Customer"
healthCheck 8024 "Gateway"
healthCheck 8023 "Authorization"

echo "All Rekindle Bookstore services are up"
exit 0
