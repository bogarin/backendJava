package com.curso.api.service.auth.impl;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.curso.api.service.auth.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long expirationInMinutes;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Override
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((expirationInMinutes * 60 * 1000) + issuedAt.getTime());

        String jwt = Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername()) // obligatorio
                .issuedAt(issuedAt) // obligatorio
                .expiration(expiration)
                .claims(extraClaims)//opcionales
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
        log.info(jwt);
        return jwt;

    }

    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(secretKey);
        log.info("mostrar lo decodifica: "+new String(passwordDecoded));
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }

}
