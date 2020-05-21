package com.yyf.mallcache.fileter;

import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//进行图片验证
public class ValidImageFileter extends OncePerRequestFilter {
    public static  final String LOGIN_URL="/userLogin";
    public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url=httpServletRequest.getRequestURL().toString();
        //只拦截登陆的post请求
        if(url.indexOf(LOGIN_URL)>=0&&httpServletRequest.getMethod().equalsIgnoreCase("POST")){
            HttpSession session = httpServletRequest.getSession();
            String checkCode =(String) session.getAttribute(CHECK_CODE_KEY);
            String validCode=httpServletRequest.getParameter("validCode");
            LoggerUtil.getLogger(this.getClass()).info(validCode);
            if(checkCode==null||!checkCode.equalsIgnoreCase(validCode)) {
                //验证码错误 直接返回
                httpServletResponse.getWriter().write(ConfigUtil.LOGIN_IMAGEFAIL);
                return;
            }

        }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
