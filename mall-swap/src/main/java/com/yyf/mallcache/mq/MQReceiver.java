
package com.yyf.mallcache.mq;

import com.yyf.mallcache.bean.SecMessage;
import com.yyf.mallcache.service.SecService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



@Service
@Lazy(false)
public class MQReceiver {
	Logger log=LoggerFactory.getLogger(MQReceiver.class);
	@Autowired
	SecService secService;
	@RabbitListener(queues=MQConfig.SEC_QUEUE)
public void receive(String message) {
		SecMessage secMessage= FastJsonUtil.stringToBean(message, SecMessage.class);
		Integer userId =secMessage.getUserId();
		Integer secproductId=secMessage.getProductId();
        // 验证用户是否重复下单 和再次验证库存
		System.out.println("出队"+userId);
		String returnStr = secService.validSecBySql(userId, secproductId);
		if(returnStr!= ConfigUtil.SEC_SUCCESS) {
			return ;	
		}
        //提交订单
		secService.submitSec(userId, secproductId);
}
}

