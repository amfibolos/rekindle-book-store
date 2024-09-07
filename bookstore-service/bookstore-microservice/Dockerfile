FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :bookstore-service:bookstore-microservice:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/bookstore-service/bookstore-microservice/build/lib/bookstore-microservice.jar .
EXPOSE 8183
CMD ["java", "-jar", "bookstore-microservice.jar"]