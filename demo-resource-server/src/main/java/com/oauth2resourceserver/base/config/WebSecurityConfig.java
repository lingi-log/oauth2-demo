package com.oauth2resourceserver.base.config;// package com.example.demo.base.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

// import com.example.demo.api.auth.service.AuthService;
// import com.example.demo.base.auth.RESTAuthenticationEntryPoint;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     @Autowired private AuthService authService;
//     @Autowired private RESTAuthenticationEntryPoint authenticationEntryPoint;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {      
//       http
//         .csrf().disable()
//         .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
//         .authorizeRequests()
//             // .antMatchers("/", "/api/auth/sign-in", "/api/auth/sign-up", "/error").permitAll()
//             // .antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
//             // .anyRequest().authenticated()
            
//             .antMatchers("/api/auth/sign-in", "/api/auth/sign-up").permitAll()
//             .antMatchers("/api/**").authenticated()
//             .anyRequest().permitAll()
//         .and()
//         .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth
//           .userDetailsService(authService)
//           .passwordEncoder(authService.passwordEncoder());
//     }

//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//          return super.authenticationManagerBean();
//     }
// }