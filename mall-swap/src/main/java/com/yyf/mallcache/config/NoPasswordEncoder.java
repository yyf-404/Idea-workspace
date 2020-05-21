package com.yyf.mallcache.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        //不做任何加密处理
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println("matches"+charSequence);
        //charSequence是前端传过来的密码，s是数据库中查到的密码
        if (charSequence.toString().equals(s)) {

            return true;
        }
        return false;
    }
}
