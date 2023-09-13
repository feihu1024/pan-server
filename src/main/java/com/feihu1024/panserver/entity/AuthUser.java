package com.feihu1024.panserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@ApiModel(value = "auth用户信息")
public class AuthUser extends User {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "主目录")
    private String homeDirectory;

    @ApiModelProperty(value = "用户角色")
    private String rule;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return this.id;
    }

    public AuthUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHomeDirectory() {
        return this.homeDirectory;
    }

    public AuthUser setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
        return this;
    }

    public String getRule() {
        return this.rule;
    }

    public AuthUser setRule(String rule) {
        this.rule = rule;
        return this;
    }
}
