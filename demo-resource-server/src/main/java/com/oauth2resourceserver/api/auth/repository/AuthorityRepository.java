package com.oauth2resourceserver.api.auth.repository;

import java.util.List;

import com.oauth2resourceserver.api.auth.repository.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
    @Query("select authorityName from Authority where id=:id")
    List<String> findAllById(@Param("id") String id);
        
}