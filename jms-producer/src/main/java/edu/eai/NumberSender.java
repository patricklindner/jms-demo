package edu.eai;

import edu.eai.domain.NumberMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NumberSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberSender.class);

    private final JmsTemplate jmsTemplate;

    public void send(String test) {
        jmsTemplate.convertAndSend("testTopic", test);
    }

    public void send(NumberMessage message) {
        LOGGER.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("numbers", message);
    }
}