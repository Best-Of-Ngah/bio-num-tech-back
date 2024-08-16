package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.entities.Action;
import com.best.of.ngah.bionumtech.services.users.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/actions")
@RequiredArgsConstructor

public class ActionController {
    private final ActionService actionService;

    @GetMapping
    public List<Action> getAllUsers() {
        return actionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Action> getUserById(@PathVariable Long id) {
        return actionService.findById(id);
    }

    @PostMapping
    public Action register(@RequestBody Action action) {
        return actionService.register(action);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        actionService.deleteById(id);
    }

}
