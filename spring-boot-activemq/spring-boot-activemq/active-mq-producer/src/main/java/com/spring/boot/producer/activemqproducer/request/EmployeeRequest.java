package com.spring.boot.producer.activemqproducer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

  private String fName;

  private String lName;

  private Long salary;

  private Integer Age;
}
