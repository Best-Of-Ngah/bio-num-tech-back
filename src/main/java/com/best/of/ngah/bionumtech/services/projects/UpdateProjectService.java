package com.best.of.ngah.bionumtech.services.projects;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.UpdateProject;

public interface UpdateProjectService {
    ProjectSummarized updateProject(UpdateProject createProjectDto);
}
