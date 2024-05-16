//package com.github.supercodinpj1.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    public static Long getCurrentMemberEmaile() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || authentication.getName() == null) {
//            throw new RuntimeException("인증 정보가 없습니다.");
//        }
//
//        return Long.parseLong(authentication.getName());
//    }
//
//}