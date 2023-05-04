package com.spring.boot.producer.activemqproducer.service;

import com.spring.boot.producer.activemqproducer.request.DepartmentRequest;
import com.spring.boot.producer.activemqproducer.request.EmployeeRequest;
import javax.jms.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerService {
  @Value("${spring.activemq.topic}")
  String topic;
  @Value("${spring.activemq.queue}")
  String queue;
  @Autowired private JmsTemplate jmsTemplate;

  public void sendToQueue(EmployeeRequest employeeDto) {
    try {
        this.jmsTemplate.convertAndSend(
          queue,
              employeeDto,
          jmsHeaderTemplate -> {
            jmsHeaderTemplate.setJMSReplyTo(new ActiveMQQueue(queue));
            jmsHeaderTemplate.setJMSPriority(Message.DEFAULT_PRIORITY);

            log.info("setting custom JMS headers before sending");
            jmsHeaderTemplate.setStringProperty(
                "jms-custom-header", "this is a custom jms property");
            jmsHeaderTemplate.setBooleanProperty("jms-custom-property", true);
            jmsHeaderTemplate.setDoubleProperty("jms-custom-property-price", 0.0);

            return jmsHeaderTemplate;
          });
    } catch (Exception e) {
      log.error("Error sending message to queue.", e);
    }
  }

  public void sendToTopic(DepartmentRequest departmentDto) {
    try {
      this.jmsTemplate.convertAndSend(topic, departmentDto,
              jmsHeaderTemplate -> {
                  jmsHeaderTemplate.setJMSReplyTo(new ActiveMQQueue(queue));
                  jmsHeaderTemplate.setJMSPriority(Message.DEFAULT_PRIORITY);

                  log.info("setting custom JMS headers before sending");
                  jmsHeaderTemplate.setStringProperty(
                          "jms-custom-header", "this is a custom jms property");
                  jmsHeaderTemplate.setBooleanProperty("jms-custom-property", true);
                  jmsHeaderTemplate.setDoubleProperty("jms-custom-property-price", 0.0);

                  return jmsHeaderTemplate;
              });
    } catch (Exception e) {
      log.error("Error sending message to queue.", e);
    }
  }
}
