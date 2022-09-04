package com.blocdao.project.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;

@AllArgsConstructor
public class SecurityConfig {
    private final OncePerRequestFilter authFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(HttpMethod.GET ,"/")
                .antMatchers(HttpMethod.POST, "/api/member/**")
                .antMatchers("/css/**")
                .antMatchers("/static/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                .antMatchers("/fonts/**")
                .antMatchers("/vendor/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/pages/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/api/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**");
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 허용할 origin (fe 로컬용 호스트, 리얼용 호스트)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://cloudwi-894c9.web.app"));
        
        // 허용할 HTTP Method 종류
        configuration.setAllowedHeaders(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        
        // 허용할 헤더
        configuration.addAllowedHeader("*");
        
        // 노출시킬 헤더
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
