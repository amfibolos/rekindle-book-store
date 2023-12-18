package com.rekindle.book.store.server.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Damian
 * @Date 03.12.2023
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServerApplication.class, args);
  }
}
