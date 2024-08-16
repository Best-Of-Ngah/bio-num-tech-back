package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectType;
import com.best.of.ngah.bionumtech.services.projects.types.ProjectTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects-types")
public class ProjectTypeController {
    private final ProjectTypeService projectTypeService;


    @PostMapping
    public ProjectType createOrUpdate(@RequestBody @Valid ProjectType toSave) {
        return projectTypeService.createOrUpdateType(toSave);
    }


    @GetMapping
    public List<ProjectType> getAllProjectTypes() {
        return projectTypeService.findAllTypes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectTypeType(@PathVariable Long id) {
        projectTypeService.deleteProjectTypeType(id);
    }
}

