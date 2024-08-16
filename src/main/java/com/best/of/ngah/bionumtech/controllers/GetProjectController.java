package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectWithTypeSummarized;
import com.best.of.ngah.bionumtech.services.projects.GetProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class GetProjectController {
    private final GetProjectService getProjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Paginate<List<ProjectSummarized>> getAllProject(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "realisationDate") String propertyToSortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return getProjectService.findAllProjects(page, size, propertyToSortBy, Sort.Direction.valueOf(direction.toUpperCase()));
    }

    @GetMapping("/{id}")
    public ProjectWithTypeSummarized getProjectWithType(@PathVariable Long id) {
        return getProjectService.findProjectWithTypeById(id);
    }
}
