#!/bin/bash

# Kill processes matching specific commandlines
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

# kill_processes function
kill_processes "eureka-server.jar"
kill_processes "config-server.jar"
kill_processes "authorization-server.jar"
kill_processes "gateway-server.jar"
kill_processes "customer-microservice.jar"
kill_processes "bookstore-microservice.jar"
kill_processes "order-microservice.jar"
kill_processes "payment-microservice.jar"
exit 0