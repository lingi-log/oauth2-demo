package com.oauth2authserver;

import com.oauth2authserver.constant.UserRole;
import com.oauth2authserver.entity.OauthClientDetails;
import com.oauth2authserver.entity.ResourceOwner;
import com.oauth2authserver.repository.OauthClientDetailsRepository;
import com.oauth2authserver.repository.ResourceOwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OAuth2AuthorizationServer implements CommandLineRunner{
	@Autowired private ResourceOwnerRepository resourceOwnerRepository;
	@Autowired private OauthClientDetailsRepository oauthClientDetailsRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
        System.out.println("OAuth 2 Authorization Server");
		SpringApplication.run(OAuth2AuthorizationServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		ResourceOwner user = new ResourceOwner();
		user.setId(1l);
		user.setUsername("jangho21@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setRole(UserRole.ROLE_USER);
		resourceOwnerRepository.save(user);

		OauthClientDetails clientDetails = new OauthClientDetails();
		clientDetails.setClientId("clientapp");
		clientDetails.setResourceIds(null);
		clientDetails.setClientSecret(passwordEncoder.encode("1234"));
		clientDetails.setScope("read_profile,read_posts");
		clientDetails.setAuthorizedGrantTypes("authorization_code,refresh_token,password,implicit,client_credentials");
		clientDetails.setWebServerRedirectUri("http://localhost:9000/callback");
		clientDetails.setAuthorities(null);
		clientDetails.setAccessTokenValidity(3600);
		clientDetails.setRefreshTokenValidity(3600);
		clientDetails.setAdditionalInformation(null);
		clientDetails.setAutoapprove(false);
		oauthClientDetailsRepository.save(clientDetails);

	}
}
