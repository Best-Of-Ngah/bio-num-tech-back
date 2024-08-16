package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.actions.ActionsPerProject;
import com.best.of.ngah.bionumtech.services.statistic.ActionsPerProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionPerProjectController {
    private final ActionsPerProjectService actionsPerProjectService;

    @GetMapping("/count/liked/{projectId}")
    public ActionsPerProject countLikeByProject(@PathVariable Long projectId){
        return actionsPerProjectService.countLikeByProject(projectId);
    }
}
