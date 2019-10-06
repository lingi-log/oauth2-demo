package com.oauth2authserver.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@ToString
public class ClientDto {
	
	private String name;
	private String redirectUri;
	private String clientType;
}