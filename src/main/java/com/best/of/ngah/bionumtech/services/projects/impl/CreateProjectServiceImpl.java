package com.best.of.ngah.bionumtech.services.projects.impl;

import com.best.of.ngah.bionumtech.dtos.projects.CreateProject;
import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.entities.Project;
import com.best.of.ngah.bionumtech.entities.Type;
import com.best.of.ngah.bionumtech.entities.User;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.files.FileService;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.projects.CreateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CreateProjectServiceImpl implements CreateProjectService {
    private final FileService fileService;
    private final RepositoryFactory repository;

    @Override
    public ProjectSummarized createProject(CreateProject toCreate) {
        var foundUser = repository.getUserRepository().findById(toCreate.getUserId())
                .orElseThrow(() -> new HttpNotFoundException("User not found"));
        var projectType = findProjectTypeById(toCreate.getTypeId());
        var url = saveFileAndGetUrl(toCreate.getFile());
        var project = createProjectEntity(foundUser, projectType, toCreate, url);
         var savedProject = repository.getProjectRepository().save(project);
        return summarizeProject(savedProject);
    }

    private Type findProjectTypeById(Long typeId) {
        return repository.getProjectTypeRepository().findById(typeId)
                .orElseThrow(() -> new HttpNotFoundException("Project type not found"));
    }

    private String saveFileAndGetUrl(MultipartFile file) {
        return fileService.saveFile(file);
    }

    private Project createProjectEntity(User user, Type type, CreateProject dto, String imageUrl) {
        return Project.builder()
                .type(type)
                .user(user)
                .budget(dto.getBudget())
                .image(imageUrl)
                .status(dto.getStatus())
                .description(dto.getDescription())
                .realisationDate(dto.getRealisationDate())
                .requestDate(dto.getRequestDate())
                .build();
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
