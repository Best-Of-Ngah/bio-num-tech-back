package com.best.of.ngah.bionumtech.services.projects.types;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectType;
import com.best.of.ngah.bionumtech.entities.Type;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.repositories.ProjectTypeRepository;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTypeServiceImpl implements ProjectTypeService {
    private final RepositoryFactory repository;


    @Transactional
    @Override
    public ProjectType createOrUpdateType(ProjectType type) {
        if (type.getId() != null) {
            var existingType = getProjectTypeRepository().findById(type.getId())
                    .orElseThrow(() -> new HttpNotFoundException("Project type not found !"));
            existingType.setName(type.getName());
            var savedProject = getProjectTypeRepository().save(existingType);
            return convertToDto(savedProject);
        } else {
            var savedType = getProjectTypeRepository()
                    .save(Type.builder().name(type.getName()).build());
            return convertToDto(savedType);
        }
    }

    @Override
    public List<ProjectType> findAllTypes() {
        return getProjectTypeRepository().findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteProjectTypeType(Long id) {
        if (getProjectTypeRepository().existsById(id)) {
            getProjectTypeRepository().deleteById(id);
        } else {
            throw new HttpNotFoundException("Project type not exist");
        }
    }

    private ProjectType convertToDto(Type type) {
        return new ProjectType(type.getId(), type.getName());
    }

    private ProjectTypeRepository getProjectTypeRepository() {
        return repository.getProjectTypeRepository();
    }
}
