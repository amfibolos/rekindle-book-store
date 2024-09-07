FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :customer-service:customer-microservice:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/customer-service/customer-microservice/build/lib/customer-microservice.jar .
EXPOSE 8184
CMD ["java", "-jar", "customer-microservice.jar"]