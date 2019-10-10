package com.oauth2authserver.security.config;

import java.io.PrintWriter;
import java.util.Arrays;

import com.oauth2authserver.security.CustomAuthenticationProvider;
import com.oauth2authserver.security.handler.CustomAccessDeniedHandler;
import com.oauth2authserver.security.handler.CustomAuthenticationFailureHandler;
import com.oauth2authserver.security.handler.CustomAuthenticationSuccessHandler;
import com.oauth2authserver.security.filter.ResourceOwnerAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * This class actually authenticates requests.
 *
 * @author janghohan
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.antMatchers("/", "/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.and().httpBasic().and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().headers().frameOptions().disable();
//			.cors()
//				.and()
//			.authorizeRequests()
//				.antMatchers("/error**", "/login*/**", "/h2-console/**").permitAll()
//				.and()
//				.formLogin().permitAll().and()
//			.authorizeRequests()
//				.antMatchers("/api/**").authenticated()
//				.and()
//			.csrf().disable()
//			.headers().frameOptions().disable()
//				.and()
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
////			.addFilterBefore(authenticationFilter(), ResourceOwnerAuthenticationFilter.class)
//		.exceptionHandling()
//			  .authenticationEntryPoint(authenticationEntryPoint())
//			  .accessDeniedHandler(accessDeniedHandler())
		;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}


	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return authenticationProvider;
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/loginPage");
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/index");

		return successHandler;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/loginPage?error=loginfali");

		return failureHandler;
	}
}