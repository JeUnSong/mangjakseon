package com.mangjakseon.config;

import com.mangjakseon.security.handler.LoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 패스워드 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.formLogin()
                .loginPage("/login-page")   // 로그인 화면
                .loginProcessingUrl("/login")   // 로그인 처리시 사용되는 페이지
                .defaultSuccessUrl("/home");   // 로그인 성공시 보여줄 페이지
        http.csrf().disable();  // csrf 토큰 비활성화
        http.logout();

        http.oauth2Login(); // 소셜 로그인 처리
        http.oauth2Login().successHandler(successHandler());
    }

    @Bean
    public LoginSuccessHandler successHandler(){
        return new LoginSuccessHandler();
    }
}
