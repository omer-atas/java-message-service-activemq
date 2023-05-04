package com.spring.boot.producer.activemqproducer.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.boot.producer.activemqproducer.request.DepartmentRequest;
import com.spring.boot.producer.activemqproducer.request.EmployeeRequest;
import com.spring.boot.producer.activemqproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProducerController {

  private ProducerService producerService;

  @Autowired
  public ProducerController(ProducerService producerService) {
    this.producerService = producerService;
  }

  @RequestMapping(
      value = {"/sendToQueue"},
      method = RequestMethod.POST)
  public ResponseEntity<Void> sendToQueue(@RequestBody EmployeeRequest employeeRequest) {
    this.producerService.sendToQueue(employeeRequest);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(
      value = {"/sendToTopic"},
      method = RequestMethod.POST)
  public ResponseEntity<Void> sendToTopic(@RequestBody DepartmentRequest departmentRequest) {
    this.producerService.sendToTopic(departmentRequest);
    return ResponseEntity.ok().build();
  }
}
