package com.xuh.meituan.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutSenderConf {

    @Bean("Aqueue")
    public Queue aqueue(){
        return new Queue("queue.a");
    }
    @Bean("Bqueue")
    public Queue bqueue(){
        return new Queue("queue.b");
    }
    @Bean("Cqueue")
    public Queue cqueue(){
        return new Queue("queue.c");
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 交换机和队列绑定
     * @param messageQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessageA(@Qualifier("Aqueue")Queue messageQueue
            ,@Qualifier("fanoutExchange")FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageQueue).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeMessageB(@Qualifier("Bqueue")Queue messageQueue
            ,@Qualifier("fanoutExchange")FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageQueue).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeMessageC(@Qualifier("Cqueue")Queue messageQueue
            ,@Qualifier("fanoutExchange")FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageQueue).to(fanoutExchange);
    }
}
