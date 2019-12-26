package com.yyf.controller;

import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/toLogin")
   public String toLogin(@RequestParam("userName") String userName, @RequestParam("password")String password,
                         HttpServletRequest request, Map<String, Object> map){
        if(userName!=null&&!userName.equals("")&&password.equals("123456")){
            System.out.println("userName"+userName+"passwd"+password );
            HttpSession session=request.getSession();
            session.setAttribute("user",userName);
            return "redirect:main.html";
        }
        System.out.println("登陆用户名或者密码错误");
       map.put("msg","登陆用户名或者密码错误");
       return "login";
   }

}
