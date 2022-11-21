package edu.eai.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class AdditionReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionReceiver.class);

    @JmsListener(destination = "numbers")
    @SendTo("results")
    public int receive(String message) {
        String[] input = message.split(",");
        int number1 = Integer.parseInt(input[0]);
        int number2 = Integer.parseInt(input[1]);

        LOGGER.info("Adding numbers: {} + {}", number1, number2);
        int result = number1+number2;
        LOGGER.info("Result: {}", result);
        return result;
    }

}