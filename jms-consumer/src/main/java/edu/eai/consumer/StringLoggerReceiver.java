package edu.eai.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "logger")
@Slf4j
public class StringLoggerReceiver {

//      DEBUG Listener
//    @JmsListener(destination = "numbers")
//    public void receiveNumbers(NumberMessage message) {
//        log.info("Received message on topic numbers: {}", message);
//    }

    @JmsListener(destination = "results")
    public void receiveResults(ActiveMQTextMessage message) throws JMSException {
        log.info("Received: {} from producer: {}", message.getText(), message.getProducerId().getConnectionId());
    }

}
