package com.spring.boot.producer.activemqproducer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

  private Integer deptId;

  private String deptName;

  private Integer noOfEmployees;
}
