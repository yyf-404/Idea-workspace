package com.yyf.mallcache.config;


import com.yyf.mallcache.fileter.UserCacheSuccessHandler;
import com.yyf.mallcache.fileter.UserLogoutSuccessHandler;
import com.yyf.mallcache.fileter.ValidImageFileter;
import com.yyf.mallcache.service.SimpleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String USER = "USER";//普通用户
    public static final String ROLE_PREFIX = "ROLE_";
    @Autowired
    SimpleUserDetailsService simpleUserDetailsService;
    @Autowired
    UserCacheSuccessHandler userCacheSuccessHandler;
    @Autowired
    UserLogoutSuccessHandler userLogoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/person/**", "/shopcart").hasRole(SecurityConfig.USER);
        //关闭csrf 允许ajax请求
        http.csrf().disable();
        http.formLogin().loginPage("/login").loginProcessingUrl("/userLogin")
                .usernameParameter("loginId").passwordParameter("loginPassword")
                .successHandler(userCacheSuccessHandler);
        http.logout().logoutUrl("/userLogout").logoutSuccessUrl("/").logoutSuccessHandler(userLogoutSuccessHandler);
        http.addFilterBefore(new ValidImageFileter(), UsernamePasswordAuthenticationFilter.class);
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //必须定义passwordEncoder 对密码进行验证
        //springSecurity5.0以后没有默认的NoPasswordEncoder 只能自定义NoPasswordEncoder
        auth.userDetailsService(simpleUserDetailsService).passwordEncoder(new NoPasswordEncoder());

    }
}
