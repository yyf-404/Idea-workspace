
package com.yyf.mallcache.mq;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQConfig {
	public static final String QUEUE="queue";
	public static final String SEC_QUEUE="sec.queue";
	

	@Bean
	public Queue queue() {
		return new Queue(QUEUE,true);//队列名称 是否持久化
	}
	
	@Bean
	public Queue secQueue() {
	
		return new Queue(SEC_QUEUE,true);
	}


}

