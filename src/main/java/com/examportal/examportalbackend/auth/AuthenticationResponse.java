package com.examportal.examportalbackend.auth;

import com.examportal.examportalbackend.dto.AuthenticatedUserDto;

public class AuthenticationResponse {

    private String token;
    private Long expirationDate;

    private AuthenticatedUserDto authenticatedUser;


    public AuthenticationResponse(String token, Long expirationDate, AuthenticatedUserDto authenticatedUser) {
        this.token = token;
        this.expirationDate = expirationDate;
        this.authenticatedUser = authenticatedUser;
    }

    public AuthenticationResponse(String token, Long expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AuthenticatedUserDto getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(AuthenticatedUserDto authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}
