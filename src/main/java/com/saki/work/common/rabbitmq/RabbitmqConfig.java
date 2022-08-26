package com.saki.work.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class RabbitmqConfig {
    public final static String TOPIC_ONE = "topic.one";
    public final static String TOPIC_EXCHANGE = "topicExchange";

    @Bean
    public Queue queue_one(){
        return new Queue(TOPIC_ONE);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeOne(Queue queue_one, TopicExchange exchange){
        return BindingBuilder.bind(queue_one).to(exchange).with("topic.one");
    }
}
