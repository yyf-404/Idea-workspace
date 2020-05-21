package com.yyf.mallcache.fileter;

import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//进行图片验证
public class UserCacheFileter extends OncePerRequestFilter {
    public static  final String LOGIN_URL="/userLogin";
    public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

            filterChain.doFilter(httpServletRequest,httpServletResponse);



    }
}
