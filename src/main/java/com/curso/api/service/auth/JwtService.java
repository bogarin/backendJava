package com.curso.api.service.auth;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails user, Map<String, Object> extraClaims);

    public String extractUsername(String jwt) ;

}
