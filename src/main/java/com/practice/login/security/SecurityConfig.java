package com.practice.login.security;

import com.practice.login.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final AuthenticationEntryPointImpl authenticationEntryPoint;

    final LogoutSuccessHandlerImpl logoutSuccessHandler;

    final
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    final
    AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    public SecurityConfig(AuthenticationEntryPointImpl authenticationEntryPoint, AuthenticationSuccessHandlerImpl authenticationSuccessHandler, LogoutSuccessHandlerImpl logoutSuccessHandler, AuthenticationFailureHandlerImpl authenticationFailureHandler, AccessDeniedHandlerImpl accessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();

            http.authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("admin")
                    .antMatchers("/user/**").hasAuthority("user")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                    .and()
                    .formLogin()
                    .permitAll()
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                    .rememberMe();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(11);
        return encoder;
    }

}
