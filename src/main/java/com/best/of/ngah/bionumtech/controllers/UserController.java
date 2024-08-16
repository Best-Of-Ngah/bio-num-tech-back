package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.users.UpdateUser;
import com.best.of.ngah.bionumtech.dtos.users.UserTemplate;
import com.best.of.ngah.bionumtech.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping
    public Paginate<List<UserTemplate>> getAllUsers(
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage
    ) {
        return userService.findAllUsers(pageSize, currentPage);
    }

    @PostMapping
    public UserTemplate updateUser(
            @RequestParam String id, @RequestParam String email, @RequestParam String password, @RequestParam(name = "file") MultipartFile image
    ) {
        var newUser = new UpdateUser(Long.valueOf(id), email, password, image);
        return userService.updateUser(newUser);
    }

    @GetMapping("/{id}")
    public UserTemplate findById(@PathVariable String id) {
        return userService.findById(id);
    }
}
