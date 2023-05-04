package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActiveMqProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActiveMqProducerApplication.class, args);
  }
}
