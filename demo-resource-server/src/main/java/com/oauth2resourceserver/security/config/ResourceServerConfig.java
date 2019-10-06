package com.oauth2resourceserver.security.config;

import com.oauth2resourceserver.security.entrypoint.CustomAuthenticationEntryPoint;
//import com.oauth2resourceserver.security.filters.OAuth2AuthenticationFilter;
import com.oauth2resourceserver.security.handler.CustomAccessDeniedHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;



@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired private CustomAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//OAuth2.0 토큰 인증을 받아야하는 요청들 규칙정리
			.authorizeRequests()
				.antMatchers("/api/**").authenticated()
			;
	}

	/*
	 * ResourceTokenService는 Resource Server가 액세스 토큰의 유효성을 검사하기 위해
	 * 사용된다. 해당 서비스 클래스는 tokenStore()에 어떠한 스토어가 설정되냐에 의존적으로 수행된다.
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources
			.authenticationEntryPoint(authenticationEntryPoint)
			.accessDeniedHandler(customAccessDeniedHandler)
			.tokenStore(tokenStore());
	}


	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("non-prod-signature");
		return converter;
	}
    
	@Bean
	public TokenStore tokenStore() {
		JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		return tokenStore;
	}
}