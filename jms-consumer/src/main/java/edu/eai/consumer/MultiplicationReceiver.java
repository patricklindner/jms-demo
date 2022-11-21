package edu.eai.consumer;

import edu.eai.domain.NumberMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "multiplication")
public class MultiplicationReceiver {

    public MultiplicationReceiver() {
        LOGGER.info("Multiplication Receiver started.");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplicationReceiver.class);

    @JmsListener(destination = "numbers")
    @SendTo("results")
    public String receive(NumberMessage message) {
        LOGGER.info("Multiplying numbers: {} * {}", message.getNumber1(), message.getNumber2());
        int result = message.getNumber1() * message.getNumber2();
        LOGGER.info("Result: {}", result);
        return "Result of multiplication: " + result;
    }

}