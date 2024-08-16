package com.best.of.ngah.bionumtech.services.projects;

import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectWithTypeSummarized;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GetProjectService {
    Paginate<List<ProjectSummarized>> findAllProjects(Integer page, Integer currentPage,String propertyToSortBy, Sort.Direction direction);

    ProjectWithTypeSummarized findProjectWithTypeById(Long id);
}
