package com.best.of.ngah.bionumtech.services.users;


import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.entities.User;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtHelperService {
    AuthToken generateToken(User user);

    boolean isValid(String token, User user);

    <T> T extractClaim(String token, Function<Claims, T> fn);

    String extractUsername(String token);
}
