package com.oauth2client.repository;

import com.oauth2client.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceOwnerRepository extends JpaRepository<ResourceOwner, Long>{
	public ResourceOwner findByUsername(String username);
}