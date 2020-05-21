package com.yyf.mallcache.service;

import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.config.SecurityConfig;
import com.yyf.mallcache.mapper.UserMapper;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoggerUtil.getLogger(this.getClass()).info("username="+s);
        User user=userMapper.selectByLoginId(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在"); // 若不存在抛出用户不存在异常
        }
        // 权限字符串转化
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        //可以通过这样的方式添加roles
        String[] roles ={SecurityConfig.ROLE_PREFIX+SecurityConfig.USER} ;// 获取后的Roles必须有ROLE_前缀，否则会抛Access is denied无权限异常
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        // 交给security进行验证并返回
        return new org.springframework.security.core.userdetails.User(user.getUserId().toString(),user.getLoginPassword(), simpleGrantedAuthorities);
    }
}
