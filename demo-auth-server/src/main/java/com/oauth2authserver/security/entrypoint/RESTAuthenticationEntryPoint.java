package com.oauth2authserver.security.entrypoint;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// org.springframework.security.crypto.password.DelegatingPasswordEncoder
// import org.apache.catalina.core.ApplicationDispatcher;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

@Component
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
        authException.printStackTrace();
        if(authException instanceof BadCredentialsException){
          // 입력 정보 안맞음
          response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
          // 권한 없이 요청
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
  }
}