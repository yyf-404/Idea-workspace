
package com.yyf.mallcache.mq;

import com.yyf.mallcache.util.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service
public class MQSender {
	@Autowired
	AmqpTemplate amqpTemplate;

	//Logger log=LoggerFactory.getLogger(MQSender.class);
public void send (Object message) {
	String msg= FastJsonUtil.beanToString(message);
	System.out.println("send入队"+message);
	amqpTemplate.convertAndSend(MQConfig.SEC_QUEUE,msg);

}
}

