package com.oauth2authserver.repository;

import com.oauth2authserver.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceOwnerRepository extends JpaRepository<ResourceOwner, Long>{

	public ResourceOwner findByUsername(String username);

}