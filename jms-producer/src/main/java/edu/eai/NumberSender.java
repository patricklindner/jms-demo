package edu.eai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class NumberSender {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(NumberSender.class);

    private final JmsTemplate jmsTemplate;

    public NumberSender(JmsTemplate jmsTemplate) throws JMSException {
        this.jmsTemplate = jmsTemplate;
        jmsTemplate.getConnectionFactory().createConnection().createSession().createTopic("numbers");
    }

    public void send(String message) {
        LOGGER.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("numbers", message);
    }
}