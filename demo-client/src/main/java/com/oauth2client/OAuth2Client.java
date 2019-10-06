package com.oauth2client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.oauth2client.constant.UserRole;
import com.oauth2client.entity.ResourceOwner;
import com.oauth2client.repository.ResourceOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OAuth2Client  implements CommandLineRunner, ServletContextInitializer{
	
	@Autowired private ResourceOwnerRepository repository;
	@Autowired private PasswordEncoder passwordEncoder;
	
    public static void main( String[] args ){
        SpringApplication.run(OAuth2Client.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
		
		ResourceOwner user = new ResourceOwner();
		user.setId(1l);
		user.setUsername("jangho21@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setRole(UserRole.ROLE_USER);
		
		repository.save(user);
		
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.getSessionCookieConfig().setName("clientsession");
	}
    
}