package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.services.projects.DeleteProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class DeleteProjectController {
    private final DeleteProjectService deleteProjectService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id) {
        deleteProjectService.deleteProject(id);
    }

}
