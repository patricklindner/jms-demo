package edu.eai.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import java.util.List;
import java.util.UUID;

@EnableJms
@Configuration
@Slf4j
public class JmsConfig {

    @Value("${clientId:null}")
    private String clientId;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        if(clientId.equals("null")) {
            clientId = UUID.randomUUID().toString();
        }
        log.info("Creating connection with clientId: {}", clientId);
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setTrustedPackages(List.of("edu.eai"));
        activeMQConnectionFactory.setClientID(clientId);
        activeMQConnectionFactory.setConnectionIDPrefix(clientId);
        return activeMQConnectionFactory;
    }

    @Bean("jmsListenerContainerFactory")
    @ConditionalOnProperty(name = "channel", havingValue = "queue")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryQueue(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }

    @Bean("jmsListenerContainerFactory")
    @ConditionalOnProperty(name = "channel", havingValue = "topic")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryTopic(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}
