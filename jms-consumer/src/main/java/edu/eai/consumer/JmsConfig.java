package edu.eai.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import java.util.List;

@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setTrustedPackages(List.of("edu.eai"));
        return activeMQConnectionFactory;
    }

    @Bean
    @ConditionalOnProperty(name = "channel", havingValue = "queue")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryQueue() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(receiverActiveMQConnectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    @ConditionalOnProperty(name = "channel", havingValue = "topic")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryTopic() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(receiverActiveMQConnectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

}
