package com.examportal.examportalbackend.auth;

import com.examportal.examportalbackend.model.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

@Builder
public class AuthenticatedUserDto {

    private String userName;
    private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

    public AuthenticatedUserDto() {
    }

    public AuthenticatedUserDto(String userName, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.authorities = authorities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public static AuthenticatedUserDto convertToDto(User user){
        return AuthenticatedUserDto.builder()
                .userName(user.getUserName())
                .authorities(user.getAuthorities())
                .build();
    }

}
