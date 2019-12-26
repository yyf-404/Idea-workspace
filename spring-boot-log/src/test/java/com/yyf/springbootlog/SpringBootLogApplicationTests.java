package com.yyf.springbootlog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLogApplicationTests {

    @Test
    void contextLoads() {
        Logger logger =LoggerFactory.getLogger(SpringBootLogApplicationTests.class);
        logger.trace("trace logs...");
        logger.debug("debug logs...");
        logger.info("info logs...");
        logger.warn("warn logs...");
        logger.error("error logs...");

    }

}
