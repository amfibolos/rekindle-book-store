FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :order-service:order-microservice:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/order-service/order-microservice/build/lib/order-microservice.jar .
EXPOSE 8181
CMD ["java", "-jar", "order-microservice.jar"]