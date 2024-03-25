package com.chervonnaya.library.config;

import com.chervonnaya.library.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/api/author/**", "/api/book/**", "/api/copy/**",
                                "/api/reader/**", "/api/rental/**", "/api/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/author", "/api/book", "/api/copy",
                                "/api/reader", "/api/rental", "/api/user")
                        .hasAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.PUT,"/api/author", "/api/book", "/api/copy",
                                "/api/reader", "/api/rental", "/api/user")
                        .hasAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.DELETE,"/api/author/**", "/api/book/**", "/api/copy/**",
                                "/api/reader/**", "/api/rental/**", "/api/user/**")
                        .hasAuthority(Role.ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
