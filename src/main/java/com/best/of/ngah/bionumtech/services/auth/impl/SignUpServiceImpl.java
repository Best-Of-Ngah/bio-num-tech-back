package com.best.of.ngah.bionumtech.services.auth.impl;

import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.CreateUser;
import com.best.of.ngah.bionumtech.entities.User;
import com.best.of.ngah.bionumtech.enums.RoleName;
import com.best.of.ngah.bionumtech.files.FileService;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.auth.SignUpService;
import com.best.of.ngah.bionumtech.services.users.JwtHelperService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final RepositoryFactory repositoryFactory;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelperService jwtHelperService;
    private final FileService fileService;


    @Transactional
    @Override
    public AuthToken signUp(@NonNull CreateUser user) {
        var password = passwordEncoder.encode(user.getPassword());
        var newUser = User.builder()
                .email(user.getEmail())
                .password(password)
                .role(RoleName.USER)
                .build();
        var image = fileService.saveFile(user.getFile());
        newUser.setImage(image);
        var savedUser = repositoryFactory.getUserRepository().save(newUser);
        var authToken = jwtHelperService.generateToken(savedUser);
        savedUser.setToken(authToken.getToken());
        repositoryFactory.getUserRepository().save(savedUser);
        return authToken;
    }
}
