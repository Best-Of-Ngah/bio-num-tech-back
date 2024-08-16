package com.best.of.ngah.bionumtech.services.statistic;

import com.best.of.ngah.bionumtech.dtos.actions.ActionsPerProject;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActionsPerProjectService {

    private final RepositoryFactory repository;


    public ActionsPerProject countLikeByProject(Long projectId) {
        return repository.getActionRepository()
                .findActionsPerProjectWithCounts(projectId)
                .orElseThrow(() -> new HttpNotFoundException("Project not found !"));
    }
}
