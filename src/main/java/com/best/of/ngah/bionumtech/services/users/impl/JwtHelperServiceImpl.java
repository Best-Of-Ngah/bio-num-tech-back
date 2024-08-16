package com.best.of.ngah.bionumtech.services.users.impl;

import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.entities.User;
import com.best.of.ngah.bionumtech.securities.JwtProperties;
import com.best.of.ngah.bionumtech.services.users.JwtHelperService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtHelperServiceImpl implements JwtHelperService {
    private final String secretKey;
    private final long expirationDate;

    public JwtHelperServiceImpl(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getSigningKey();
        this.expirationDate = jwtProperties.getExpirationDate();
    }


    @Override
    public AuthToken generateToken(User user) {
        var now = System.currentTimeMillis();
        var expiration = new Date(now + expirationDate);
        var token = Jwts.builder()
                .subject(user.getEmail() != null ? user.getEmail() : user.getUsername())
                .issuedAt(new Date(now))
                .expiration(expiration)
                .signWith(getSecretKey())
                .compact();
        return new AuthToken("Bearer", token);
    }


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> fn) {
        Claims claims = this.extractAllClaims(token);
        return fn.apply(claims);
    }

    @Override
    public boolean isValid(String token, User user) {
        var username = extractUsername(token);
        return (username.equals(user.getEmail()) || username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
