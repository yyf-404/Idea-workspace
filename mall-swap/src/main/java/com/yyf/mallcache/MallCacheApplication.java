package com.yyf.mallcache;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@EnableRedisHttpSession
@EnableRabbit  //开启基于注解的RabbitMQ模式
@MapperScan(value ="com.yyf.mallcache.mapper")
@SpringBootApplication
@EnableCaching
public class MallCacheApplication {

    @Autowired
   static DataSource dataSource;
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(MallCacheApplication.class, args);
    }
    //UsernamePasswordAuthenticationFilter
}
