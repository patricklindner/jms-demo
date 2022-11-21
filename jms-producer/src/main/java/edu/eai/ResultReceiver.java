package edu.eai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ResultReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultReceiver.class);

    @JmsListener(destination = "results")
    public void receive(String message) {
        LOGGER.info("received result='{}'", message);
    }

    @JmsListener(destination = "numbers")
    public void receiveNumbers(String message) {
        LOGGER.info("received numbers='{}'", message);
    }



}