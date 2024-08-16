package com.best.of.ngah.bionumtech.services.projects.types;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectType;

import java.util.List;

public interface ProjectTypeService {
    ProjectType createOrUpdateType(ProjectType type);

    List<ProjectType> findAllTypes();

    void deleteProjectTypeType(Long id);
}
