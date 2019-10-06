package com.oauth2resourceserver.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
// org.springframework.security.crypto.password.DelegatingPasswordEncoder
// import org.apache.catalina.core.ApplicationDispatcher;
/**
 * @author jangho.han
 * 
 * June 11, 2019
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
      log.info("CustomAuthenticationEntryPoint.commence :::: {}", authException.getMessage());
        if(authException instanceof BadCredentialsException){
          // 입력 정보 안맞음
          response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
          // 권한 없이 요청
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
  }
}