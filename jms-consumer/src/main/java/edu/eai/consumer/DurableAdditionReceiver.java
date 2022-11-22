package edu.eai.consumer;

import edu.eai.domain.NumberMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="mode", havingValue = "durableAddition")
public class DurableAdditionReceiver {

    public DurableAdditionReceiver() {
        LOGGER.info("Addition Receiver started.");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DurableAdditionReceiver.class);

    @JmsListener(destination = "numbers", subscription = "durableAdder")
    @SendTo("results")
    public String receive(NumberMessage message) {
        LOGGER.info("Adding numbers: {} + {}", message.getNumber1(), message.getNumber2());
        int result = message.getNumber1() + message.getNumber2();
        LOGGER.info("Result: {}", result);
        return "Result of addition: "+ result;
    }

}