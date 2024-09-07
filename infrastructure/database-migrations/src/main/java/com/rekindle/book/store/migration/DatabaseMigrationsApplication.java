package com.rekindle.book.store.migration;

import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseMigrationsApplication {

  public static void main(String[] args) {
    SpringApplication.run(DatabaseMigrationsApplication.class, args);
    var host = Optional.ofNullable(System.getenv().get("POSTGRES_HOST")).orElse("localhost");
    var url = "jdbc:postgresql://" + host + ":5432/postgres";
    var user = Optional.ofNullable(System.getenv().get("POSTGRES_USER")).orElse("postgres");
    var password = Optional.ofNullable(System.getenv().get("POSTGRES_PASS")).orElse("admin");
    Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
    flyway.migrate();
  }

}
