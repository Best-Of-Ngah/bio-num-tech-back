package com.best.of.ngah.bionumtech.services.auth.impl;

import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.SignInUser;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.auth.SignInService;
import com.best.of.ngah.bionumtech.services.users.JwtHelperService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final JwtHelperService jwtHelperService;
    private final RepositoryFactory repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public @NonNull AuthToken signIn(@NonNull SignInUser user) {
        var foundUser = repository.getUserRepository()
                .findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var authToken = jwtHelperService.generateToken(foundUser);
        authToken.setToken(authToken.getToken());
        repository.getUserRepository().save(foundUser);
        return authToken;
    }
}
