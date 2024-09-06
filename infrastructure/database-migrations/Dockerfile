FROM gradle:8.5 as build
WORKDIR /app
COPY . .
RUN gradle build
ENTRYPOINT ["gradle", "flywayMigrate"]