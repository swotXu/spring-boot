package com.xuh.ticket12306.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConf {
    public static String TICKET_QUEUE = "ticket.queue.response";
    public static String TICKET_EXCHANGE = "ticket.exchange.response";
    public static String TICKET_ROUTEKEY = "ticket.routekey.response";
    /**
     * 创建抢票队列
     * @return
     */
    @Bean("ticket-queue")
    public Queue queue(){
        return new Queue(TICKET_QUEUE);
    }

    /**
     * 创建抢票交换机
     * @return
     */
    @Bean("ticket-exchange")
    public TopicExchange exchange(){
        return new TopicExchange(TICKET_EXCHANGE);
    }

    /**
     * 交换机和队列绑定
     * @param messageQueue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("ticket-queue")Queue messageQueue
            ,@Qualifier("ticket-exchange")TopicExchange exchange){
        return BindingBuilder.bind(messageQueue).to(exchange).with(TICKET_ROUTEKEY);
    }
}
