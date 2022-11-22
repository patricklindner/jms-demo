package edu.eai.consumer;

import edu.eai.domain.NumberMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "multiplication")
public class MultiplicationReceiver {

    @Value("${channel}")
    private String channel;


    public MultiplicationReceiver() {
        LOGGER.info("Multiplication Receiver started.");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplicationReceiver.class);

    @JmsListener(destination = "numbers")
    @SendTo("results")
    public String receive(NumberMessage message) throws InterruptedException {
        LOGGER.info("Multiplying numbers: {} * {}", message.getNumber1(), message.getNumber2());
        if(channel.equals("queue")) {
            LOGGER.info("Waiting for multiplication...");
            Thread.sleep(5000);
        }
        int result = message.getNumber1() * message.getNumber2();
        LOGGER.info("Result: {}", result);
        return "Result of multiplication: " + result;
    }

}