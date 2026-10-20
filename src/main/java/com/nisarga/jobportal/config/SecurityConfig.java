package com.nisarga.jobportal.config;

import com.nisarga.jobportal.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
    @Bean UserDetailsService userDetailsService(AppUserRepository users){
        return email -> users.findByEmail(email.toLowerCase().trim())
          .map(u -> User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole()).build())
          .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
    }
    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/jobs/*/apply").hasRole("APPLICANT")
                .requestMatchers("/", "/jobs", "/jobs/*", "/register", "/login", "/css/**", "/error", "/h2-console/**").permitAll()
                .requestMatchers("/employer/**").hasRole("EMPLOYER")
                .requestMatchers("/applicant/**", "/applications/**").hasRole("APPLICANT")
                .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/?logout"))
            .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }
}
