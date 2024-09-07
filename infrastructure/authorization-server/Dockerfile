FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :infrastructure:authorization-server:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/infrastructure/authorization-server/build/lib/authorization-server.jar .
EXPOSE 8023
CMD ["java", "-jar", "authorization-server.jar"]