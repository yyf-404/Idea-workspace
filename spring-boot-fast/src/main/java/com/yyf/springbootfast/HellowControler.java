package com.yyf.springbootfast;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
class HellowControler {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        String str=request.getRequestURL().toString();
        return "HelloWorld:"+str;
 }
}
