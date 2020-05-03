package com.team.app.backend.config;

import com.team.app.backend.security.jwt.JwtConfigurer;
import com.team.app.backend.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_ENDPOINT = "/api/login";
    private static final String SIGN_UP_ENDPOINT = "/api/sign-up";
    private static final String ROOT_ENDPOINT = "/";


    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers(SIGN_UP_ENDPOINT).permitAll()
                .antMatchers(ROOT_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider)).and().cors();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Stream.of("*").collect(Collectors.toList()));
        configuration.setAllowedMethods(Stream.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH").collect(Collectors.toList()));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Stream.of("Authorization", "Cache-Control", "Content-Type").collect(Collectors.toList()));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico","/index.html",
                "/assets/logo_brainduel.svg",
                "/polyfills*.js",
                "/runtime*.js",
                "/styles*.js",
                "/scripts.js",
                "/main*.js",
                "/assets/logo_brainduel.png",
                "/vendor*.js"
        );
    }
}
