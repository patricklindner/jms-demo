package edu.eai.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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
    public void receiveResults(String message) {
        log.info("Received message on topic results: {}", message);
    }

}
