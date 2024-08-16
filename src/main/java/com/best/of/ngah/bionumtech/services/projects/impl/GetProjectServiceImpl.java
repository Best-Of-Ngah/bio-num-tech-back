package com.best.of.ngah.bionumtech.services.projects.impl;

import com.best.of.ngah.bionumtech.dtos.pagination.PageInfo;
import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectType;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectWithTypeSummarized;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.projects.GetProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProjectServiceImpl implements GetProjectService {
    private final RepositoryFactory repository;

    @Override
    public Paginate<List<ProjectSummarized>> findAllProjects(Integer page, Integer pageSize, String propertyToSortBy, Sort.Direction direction) {
        Sort sort = Sort.by(direction, propertyToSortBy);
        var pageable = PageRequest.of(page - 1, pageSize, sort);
        var projectSummariesPage = repository.getProjectRepository().findAllProjects(pageable);
        var projectSummaries = projectSummariesPage.getContent();
        var totalPages = projectSummariesPage.getTotalPages();
        var hasNext = totalPages > page + 1;
        var hasPrevious = page > 0;

        var pageInfo = new PageInfo(hasNext, hasPrevious);

        return new Paginate<>(projectSummaries, pageInfo, totalPages);
    }

    @Transactional
    public ProjectWithTypeSummarized findProjectWithTypeById(Long id) {
        var projectOpt = repository.getProjectRepository().findById(id);
        if (projectOpt.isEmpty()) {
            throw new HttpNotFoundException("Project not found");
        }
        var project = projectOpt.get();

        return new ProjectWithTypeSummarized(
                project.getId(),
                project.getStatus(),
                project.getBudget(),
                project.getDescription(),
                project.getRequestDate(),
                project.getRealisationDate(),
                project.getImage(),
                project.getCreatedAt(),
                project.getUpdatedAt(),
                new ProjectType(project.getType().getId(), project.getType().getName())
        );
    }

}
