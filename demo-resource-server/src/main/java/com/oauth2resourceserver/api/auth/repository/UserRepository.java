package com.oauth2resourceserver.api.auth.repository;

import com.oauth2resourceserver.api.auth.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{

}