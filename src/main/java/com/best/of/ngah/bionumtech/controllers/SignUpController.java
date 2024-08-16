package com.best.of.ngah.bionumtech.controllers;


import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.CreateUser;
import com.best.of.ngah.bionumtech.services.auth.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
@Validated
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthToken signUp(@RequestBody @Valid CreateUser createUser) {
        return signUpService.signUp(createUser);
    }
}
