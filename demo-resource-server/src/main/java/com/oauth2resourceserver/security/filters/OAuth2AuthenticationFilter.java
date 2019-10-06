//package com.oauth2resourceserver.security.filters;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oauth2resourceserver.security.token.JwtAuthenticationToken;
//import com.oauth2resourceserver.security.token.JwtToken;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * @author jangho.han
// *
// * June 11, 2019
// *
// * Matcher에 해당되는 url이 전달될 때 실행된다.
// * attemptAuthentication이 실행되고,
// * getAuthenticationManager().authenticate()에 Http의 Authorization 에 있는 token을 인자로 넘긴다.
// */
//
//
//@Slf4j
//@Component
//public class OAuth2AuthenticationFilter extends OAuth2AuthenticationProcessingFilter {
//    private final String HEADER_SECURITY_TOKEN = "Authorization";
//
//    OAuth2AuthenticationFilter(){
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        log.info("OAuth2AuthenticationFilter.doFilter");
//        super.doFilter(req, res, chain);
//    }
//
//}
//package com.oauth2resourceserver.security.filters;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oauth2resourceserver.security.token.JwtAuthenticationToken;
//import com.oauth2resourceserver.security.token.JwtToken;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * @author jangho.han
// *
// * June 11, 2019
// *
// * Matcher에 해당되는 url이 전달될 때 실행된다.
// * attemptAuthentication이 실행되고,
// * getAuthenticationManager().authenticate()에 Http의 Authorization 에 있는 token을 인자로 넘긴다.
// */
//
//
//@Slf4j
//@Component
//public class OAuth2AuthenticationFilter extends OAuth2AuthenticationProcessingFilter {
//    private final String HEADER_SECURITY_TOKEN = "Authorization";
//
//    OAuth2AuthenticationFilter(){
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        log.info("OAuth2AuthenticationFilter.doFilter");
//        super.doFilter(req, res, chain);
//    }
//
//}
//package com.oauth2resourceserver.security.filters;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oauth2resourceserver.security.token.JwtAuthenticationToken;
//import com.oauth2resourceserver.security.token.JwtToken;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * @author jangho.han
// *
// * June 11, 2019
// *
// * Matcher에 해당되는 url이 전달될 때 실행된다.
// * attemptAuthentication이 실행되고,
// * getAuthenticationManager().authenticate()에 Http의 Authorization 에 있는 token을 인자로 넘긴다.
// */
//
//
//@Slf4j
//@Component
//public class OAuth2AuthenticationFilter extends OAuth2AuthenticationProcessingFilter {
//    private final String HEADER_SECURITY_TOKEN = "Authorization";
//
//    OAuth2AuthenticationFilter(){
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        log.info("OAuth2AuthenticationFilter.doFilter");
//        super.doFilter(req, res, chain);
//    }
//
//}
