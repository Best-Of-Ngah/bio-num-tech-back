package com.best.of.ngah.bionumtech.controllers;


import com.best.of.ngah.bionumtech.dtos.token.AuthToken;
import com.best.of.ngah.bionumtech.dtos.users.CreateUser;
import com.best.of.ngah.bionumtech.services.auth.SignUpService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sign-up")
@Validated
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthToken signUp(
            @RequestParam @NotNull String email,
            @RequestParam @NotNull String password,
            @RequestParam(name = "image") MultipartFile file
    ) {
        var toCreate = new CreateUser(email, password, file);
        return signUpService.signUp(toCreate);
    }
}
