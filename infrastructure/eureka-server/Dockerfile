FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :infrastructure:eureka-server:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/infrastructure/eureka-server/build/lib/eureka-server.jar .
EXPOSE 8761
CMD ["java", "-jar", "eureka-server.jar"]