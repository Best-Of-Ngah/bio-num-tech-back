package com.best.of.ngah.bionumtech.services.auth;

import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.SignInUser;
import lombok.NonNull;

@FunctionalInterface
public interface SignInService {
    @NonNull
    AuthToken signIn(@NonNull SignInUser user);
}

