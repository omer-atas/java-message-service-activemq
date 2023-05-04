package com.spring.boot.consumer2.activemqconsumer2.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerService {

  @JmsListener(destination = "myQueue")
  public void receiveMessageFromQueue(
      @Headers MessageHeaders headers,
      @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
      @Payload final Message jsonMessage)
      throws JMSException {
    log.info("Headers : {}", headers);
    log.info("jms-custom-property : {}", headers.get("jms-custom-property"));
    log.info("jms-custom-property-price : {}", headers.get("jms-custom-property-price"));
    log.info("jms-header-not-exists : {}", nonExistingHeader);
    log.info("Consumer 2 - Received message in 2nd queue : {} ", jsonMessage);
    if (jsonMessage instanceof TextMessage textMessage) {
      log.info("Consumer 2 - messageData in 2nd listener: {}", textMessage.getText());
    }
  }

  @JmsListener(destination = "myTopic")
  public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
    log.info("Consumer 2 - Received message in 2nd topic: {} ", jsonMessage);
    if (jsonMessage instanceof TextMessage textMessage) {
      log.info("Consumer 2 - messageData in 2nd listener: {} ", textMessage.getText());
    }
  }
}
