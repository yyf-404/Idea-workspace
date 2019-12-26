package com.yyf.springbootweb01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class SuccessController {
    @RequestMapping("/success")
     public String success(Map<String,Object> map){
        map.put("hello","你好！");
        ArrayList array=new ArrayList();
        array.add("A");
        array.add("B");
        array.add("C");
        map.put("words",array);
        return "success";

    }
}
