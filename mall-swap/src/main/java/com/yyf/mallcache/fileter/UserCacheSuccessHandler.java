package com.yyf.mallcache.fileter;

import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.mapper.UserMapper;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import com.yyf.mallcache.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class UserCacheSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisService redisService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String loginId=httpServletRequest.getParameter("loginId");
        User user=userMapper.selectByLoginId(loginId);

        LoggerUtil.getLogger(this.getClass()).info(user.toString());
        if(user!=null){
            HttpSession session=httpServletRequest.getSession();
            session.setAttribute(ConfigUtil.USERSTR,user);
            httpServletResponse.getWriter().write( ConfigUtil.LOGIN_SUCCESS);
            String tokenId= UUIDUtil.uuid();
            redisService.set(UserKey.getUserTokenKey(), tokenId, user);
            //同时在cookie中以一个固定的键（比如toke字符串），value为tokenID
            Cookie cookie=new Cookie(ConfigUtil.COOKIE_NAME_TOKEN,tokenId);
            //有效时间
            cookie.setMaxAge(UserKey.getUserTokenKey().getExpireSeconds());
            //作用路径
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }

    }
}
