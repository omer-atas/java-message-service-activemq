package com.spring.boot.consumer.activemqconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerService {

  @JmsListener(destination = "myQueue")
  @SendTo("myQueue2")
  public String receiveAndForwardMessageFromQueue(
      @Headers MessageHeaders headers,
      @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
      @Payload final Message jsonMessage)
      throws JMSException, JsonProcessingException {
    log.info("Headers : {}", headers);
    log.info("jms-custom-property : {}", headers.get("jms-custom-property"));
    log.info("jms-custom-property-price : {}", headers.get("jms-custom-property-price"));
    log.info("jms-header-not-exists : {}", nonExistingHeader);
    String messageData = null;
    log.info("Consumer 1 - Received message: {} ", jsonMessage);
    if (jsonMessage instanceof TextMessage textMessage) {
      messageData = textMessage.getText();
      log.info("Consumer 1 - MessageData: {}", messageData);
    }
    return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);
  }

  @JmsListener(destination = "myTopic")
  @SendTo("myNewTopic")
  public String receiveAndForwardMessageFromTopic(final Message jsonMessage)
      throws JMSException, JsonProcessingException {
    String messageData = null;
    log.info("Consumer 1 - Received message: {}", jsonMessage);
    if (jsonMessage instanceof TextMessage textMessage) {
      messageData = textMessage.getText();
      log.info("Consumer 1 - MessageData: {}", messageData);
    }
    return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);
  }
}
