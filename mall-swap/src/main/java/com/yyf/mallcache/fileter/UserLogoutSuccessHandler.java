package com.yyf.mallcache.fileter;

import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    RedisService redisService;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Cookie [] cookies=httpServletRequest.getCookies();
        String tokenId=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equalsIgnoreCase(ConfigUtil.COOKIE_NAME_TOKEN)){
                tokenId=cookie.getValue();
            }
        }
        LoggerUtil.getLogger(this.getClass()).info("tokenId= "+tokenId);
        redisService.del(UserKey.getUserTokenKey(),tokenId);
        HttpSession session=httpServletRequest.getSession();
        session.invalidate();
        //由于 springsecurity logoutSuccessUrl配置无序 手动实现重定向
        httpServletResponse.sendRedirect("/");
    }
  
}
