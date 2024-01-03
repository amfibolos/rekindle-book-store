@echo off
setlocal EnableDelayedExpansion

IF "%selfWrapped%"=="" (
  REM this is necessary so that we can use "exit" to terminate the batch file,
  REM and all subroutines, but not the original cmd.exe
  SET selfWrapped=true
  %ComSpec% /s /c ""%~0" %*"
  GOTO :EOF
)

set ROOTDIR=%~dp0
set EUREKA_SERVER_DIR="%ROOTDIR%infrastructure\eureka-server\build\lib\eureka-server.jar"
set CONFIG_SERVER_DIR="%ROOTDIR%infrastructure\config-server\build\lib\config-server.jar"
set GATEWAY_SERVER_DIR="%ROOTDIR%infrastructure\gateway-server\build\lib\gateway-server.jar"
set AUTH_SERVER_DIR="%ROOTDIR%infrastructure\authorization-server\build\lib\authorization-server.jar"
set BOOK_SERVER_DIR="%ROOTDIR%bookstore-service\bookstore-microservice\build\lib\bookstore-microservice.jar"
set CUSTOMER_SERVER_DIR="%ROOTDIR%customer-service\customer-microservice\build\lib\customer-microservice.jar"
set ORDER_SERVER_DIR="%ROOTDIR%order-service\order-microservice\build\lib\order-microservice.jar"
set PAYMENT_SERVER_DIR="%ROOTDIR%payment-service\payment-microservice\build\lib\payment-microservice.jar"

@rem Add default JVM options here.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set, and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%\bin\javaw.exe

if exist "%JAVA_EXE%" goto init

:init
@rem Execute servers and services

@rem Starting Eureka Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -jar "%EUREKA_SERVER_DIR%"
call :healthCheck 8761 "Eureka"

@rem Starting Config Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -jar "%CONFIG_SERVER_DIR%"
call :healthCheck 8021 "Config"

@rem Starting Gateway Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka -jar "%GATEWAY_SERVER_DIR%"

@rem Starting Authorization Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka -jar "%AUTH_SERVER_DIR%"

@rem Starting Customer Service
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka,postgre,kafka -jar "%CUSTOMER_SERVER_DIR%"

@rem Starting Bookstore Service
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka,postgre,kafka -jar "%BOOK_SERVER_DIR%"

@rem Starting Order Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka,postgre,kafka -jar "%ORDER_SERVER_DIR%"

@rem Starting Payment Server
start "" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dspring.profiles.active=eureka,postgre,kafka -jar "%PAYMENT_SERVER_DIR%"

call :healthCheck 8181 "Order"
call :healthCheck 8182 "Payment"
call :healthCheck 8183 "Bookstore"
call :healthCheck 8184 "Customer"
call :healthCheck 8023 "Authorization"
call :healthCheck 8024 "Gateway"

echo All Rekindle Bookstore services are up

goto :END

:healthCheck
set "url=http://localhost:%1/actuator/health/readiness"
set "retries=5"
set "service=%2"
set "retryCount=0"

:checkLoop
for /l %%i in (1, 1, %retries%) do (
    curl -f -s %url% | findstr /c:"{\"status\":\"UP\"}" >nul
    if !errorlevel! equ 0 (
        echo Service %service% is UP
        exit /b 0
    ) else (
        echo Waiting for %service% service to be UP (Attempt %%i of %retries%)
        timeout /nobreak /t 10 >nul
        set /a "retryCount+=1"
        if !retryCount! equ %retries% (
            goto :fail
            exit /b 1
        )
    )
)

:fail
echo One or more Rekindle Bookstore services didn't start properly. Closing all services.

wmic process where (commandline like "%%eureka-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%config-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%authorization-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%gateway-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%customer-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%bookstore-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%order-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%payment-microservice%%" and not name="wmic.exe") delete
goto :END

:END
exit