FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :infrastructure:config-server:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/infrastructure/config-server/build/lib/config-server.jar .
EXPOSE 8021
CMD ["java", "-jar", "config-server.jar"]