package com.best.of.ngah.bionumtech.services.projects;

import com.best.of.ngah.bionumtech.dtos.projects.CreateProject;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;

public interface CreateProjectService {
    ProjectSummarized createProject(CreateProject toCreate);
}
