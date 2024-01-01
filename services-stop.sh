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
            wmic process where "(commandline like '%%$process_name%%' and not name='wmic.exe')" delete
            ;;
        *)
            echo "Unsupported operating system: $OS"
            ;;
    esac
}

# Usage of the kill_processes function
kill_processes "eureka-server"

kill_processes "config-server"

kill_processes "authorization-server"

kill_processes "gateway-server"

kill_processes "customer-microservice"

kill_processes "bookstore-microservice"

kill_processes "order-microservice"

kill_processes "payment-microservice"
