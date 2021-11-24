package com.sparta.deep_sea.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @ Secured annotation 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        // 일방향 암호화(password)
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용 허용(CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                //찍어보자. 홈 화면 설정
                .antMatchers("/**").permitAll()
                //image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                //css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                //js 폴더를 login 없이 허용
                .antMatchers("/js/**").permitAll()
                //회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // login 후 index 페이지
                .antMatchers("/login/**").permitAll()
                // 그 외 어떤 요청이든 인증된 걸로 간주?
                .anyRequest().authenticated()
                .and()
                // 로그인 기능 허용
                .formLogin()
                // 로그인 view 제공: GET
                .loginPage("/user/login")
                // 로그인 입력과정 처리: POST
                .loginProcessingUrl("/user/login")
                // 성공시 default url
                .defaultSuccessUrl("/")
                // 실패시 이동할 url
                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                // 로그아웃 기능 허용
                .logout()
                // 로그아웃"처리" url
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/forbidden.html");
    }
}