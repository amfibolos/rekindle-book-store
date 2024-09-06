FROM gradle:8.5 as build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/build/libs/config-server.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "config-server.jar"]