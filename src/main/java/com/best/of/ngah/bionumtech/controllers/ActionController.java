package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized;
import com.best.of.ngah.bionumtech.dtos.actions.RestAction;
import com.best.of.ngah.bionumtech.services.users.ActionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionController {
    private final ActionService actionService;

    @GetMapping
    public List<ActionSummarized> getAllUsers() {
        return actionService.findAll();
    }

    @GetMapping("/{id}")
    public ActionSummarized getActionById(@PathVariable Long id) {
        return actionService.findById(id);
    }

    @PostMapping
    public ActionSummarized save(@RequestBody @Valid RestAction action) {
        return actionService.save(action);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        actionService.deleteById(id);
    }

}
