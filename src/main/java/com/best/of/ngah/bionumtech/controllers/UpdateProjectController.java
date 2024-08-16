package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.UpdateProject;
import com.best.of.ngah.bionumtech.services.projects.UpdateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class UpdateProjectController {

    private final UpdateProjectService updateProjectService;

    @PutMapping
    public ProjectSummarized updateProject(
            @RequestParam String projectId,
            @RequestParam String typeId,
            @RequestParam String userId,
            @RequestParam String status,
            @RequestParam String budget,
            @RequestParam String description,
            @RequestParam(name = "requestDate") String requestDateString,
            @RequestParam(name = "realisationDate") String realisationDateString,
            @RequestParam MultipartFile file
    ) {
        var requestDate = LocalDateTime.parse(requestDateString, DateTimeFormatter.ISO_DATE_TIME);
        var realisationDate = LocalDateTime.parse(realisationDateString, DateTimeFormatter.ISO_DATE_TIME);
        var createProjectDto = new UpdateProject(
                Long.valueOf(projectId),
                Long.valueOf(typeId),
                Long.valueOf(userId),
                Boolean.valueOf(status),
                Double.valueOf(budget),
                description,
                requestDate,
                realisationDate,
                file
        );
        return updateProjectService.updateProject(createProjectDto);
    }
}
