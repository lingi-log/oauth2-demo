package com.oauth2authserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter@Setter
@Table(name = "OAUTH_APPROVALS")
public class OauthApprovals {
    private static final long serialVersionUID = -3699911426704150861L;
    @Id
    private String userid;
    private String clientid;
    private String scope;
    private String status;
    private java.sql.Timestamp expiresat;
    private java.sql.Timestamp lastmodifiedat;
}
