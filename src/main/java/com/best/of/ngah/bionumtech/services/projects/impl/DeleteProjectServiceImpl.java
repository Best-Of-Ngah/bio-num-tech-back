package com.best.of.ngah.bionumtech.services.projects.impl;

import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.files.FileService;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.projects.DeleteProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProjectServiceImpl implements DeleteProjectService {
    private final FileService fileService;
    private final RepositoryFactory repository;

    @Override
    public void deleteProject(Long id) {
        var projectRepository = repository.getProjectRepository();
        var foundProject = projectRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("User not found"));
        if (foundProject.getImage() != null) {
            fileService.deleteFile(foundProject.getImage());
        }
        projectRepository.deleteById(id);
    }
}
