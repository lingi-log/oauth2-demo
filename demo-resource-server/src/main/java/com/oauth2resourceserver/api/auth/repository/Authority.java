package com.oauth2resourceserver.api.auth.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name="authority")
@Entity
public class Authority{
    @Id
    private String id;

    @Column(name="AUTHORITY_NAME", length=20, nullable=false)
    private String authorityName;

    @Builder
    public Authority(String id, String authorityName){
        this.id = id;
        this.authorityName = authorityName;
    }
}