FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :infrastructure:gateway-server:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/infrastructure/gateway-server/build/lib/gateway-server.jar .
EXPOSE 8024
CMD ["java", "-jar", "gateway-server.jar"]