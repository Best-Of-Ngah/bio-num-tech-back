package com.best.of.ngah.bionumtech.services.auth;

import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.CreateUser;
import lombok.NonNull;

@FunctionalInterface
public interface SignUpService {
    AuthToken signUp(@NonNull CreateUser user);
}
