FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :payment-service:payment-microservice:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/payment-service/payment-microservice/build/lib/payment-microservice.jar .
EXPOSE 8182
CMD ["java", "-jar", "payment-microservice.jar"]