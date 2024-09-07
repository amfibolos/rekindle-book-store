FROM gradle:8.10-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle :infrastructure:database-migrations:build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/infrastructure/database-migrations/build/lib/database-migrations.jar .
CMD ["java", "-jar", "database-migrations.jar"]