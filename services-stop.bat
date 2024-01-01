@echo off

wmic process where (commandline like "%%eureka-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%config-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%authorization-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%gateway-server%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%customer-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%bookstore-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%order-microservice%%" and not name="wmic.exe") delete
wmic process where (commandline like "%%payment-microservice%%" and not name="wmic.exe") delete