package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.projects.CreateProject;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.services.projects.CreateProjectService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class CreateProjectController {
    private final CreateProjectService projectService;

    @PostMapping
    public ProjectSummarized createProject(
            @RequestParam @NotNull String typeId,
            @RequestParam @NotNull String userId,
            @RequestParam @NotNull String status,
            @RequestParam @NotNull String budget,
            @RequestParam @NotNull String description,
            @RequestParam(name = "realisationDate") @NotNull String requestDateString,
            @RequestParam(name = "realisationDate") @NotNull String realisationDateString,
            @RequestParam MultipartFile file
    ) {
        var requestDate = LocalDateTime.parse(requestDateString, DateTimeFormatter.ISO_DATE_TIME);
        var realisationDate = LocalDateTime.parse(realisationDateString, DateTimeFormatter.ISO_DATE_TIME);
        var createProjectDto = new CreateProject(Long.valueOf(typeId), Long.valueOf(userId), Boolean.valueOf(status), Double.valueOf(budget), description, requestDate, realisationDate, file);
        return projectService.createProject(createProjectDto);
    }
}
