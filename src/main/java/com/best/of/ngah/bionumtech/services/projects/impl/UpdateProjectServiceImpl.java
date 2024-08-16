package com.best.of.ngah.bionumtech.services.projects.impl;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.dtos.projects.UpdateProject;
import com.best.of.ngah.bionumtech.entities.Project;
import com.best.of.ngah.bionumtech.entities.Type;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.files.FileService;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.projects.UpdateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UpdateProjectServiceImpl implements UpdateProjectService {
    private final RepositoryFactory repository;
    private final FileService fileService;

    @Override
    public ProjectSummarized updateProject(UpdateProject createProjectDto) {
        var foundUser = repository.getUserRepository().findById(createProjectDto.getUserId())
                .orElseThrow(() -> new HttpNotFoundException("User not found"));

        var foundProject = repository.getProjectRepository().findById(createProjectDto.getId())
                .orElseThrow(() -> new HttpNotFoundException("Project not found"));
        foundProject.setType(findProjectTypeById(createProjectDto.getTypeId()));
        foundProject.setUser(foundUser);
        foundProject.setBudget(createProjectDto.getBudget());
        foundProject.setDescription(createProjectDto.getDescription());
        foundProject.setRequestDate(createProjectDto.getRequestDate());
        foundProject.setRealisationDate(createProjectDto.getRealisationDate());
        var imageUrl = createProjectDto.getFile() != null ? saveFileAndGetUrl(createProjectDto.getFile()) : foundProject.getImage();
        foundProject.setImage(imageUrl);
        var savedProject = repository.getProjectRepository().save(foundProject);
        return summarizeProject(savedProject);
    }


    private Type findProjectTypeById(Long typeId) {
        return repository.getProjectTypeRepository().findById(typeId)
                .orElseThrow(() -> new HttpNotFoundException("Project type not found"));
    }

    private String saveFileAndGetUrl(MultipartFile file) {
        return fileService.saveFile(file);
    }

    private ProjectSummarized summarizeProject(Project project) {
        return new ProjectSummarized(
                project.getId(),
                project.getStatus(),
                project.getBudget(),
                project.getDescription(),
                project.getRequestDate(),
                project.getRealisationDate(),
                project.getImage(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

}
