package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// bean등록 : spring container에서 객체를 관리할 수 있게 만들어 하는 것
@Configuration // Bean 등록(IoC관리)
@EnableWebSecurity // 시큐리티 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;

    @Bean //IoC에 저장
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 같은해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 Test시 필수
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc")
                    .defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로챔 로그인을 대신함
    }
}
