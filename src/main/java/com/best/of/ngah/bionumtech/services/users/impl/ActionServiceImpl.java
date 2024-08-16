package com.best.of.ngah.bionumtech.services.users.impl;

import com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized;
import com.best.of.ngah.bionumtech.dtos.actions.RestAction;
import com.best.of.ngah.bionumtech.entities.Action;
import com.best.of.ngah.bionumtech.exceptions.HttpBadRequestException;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.users.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final RepositoryFactory repositoryFactory;

    @Override
    public List<ActionSummarized> findAll() {
        return repositoryFactory.getActionRepository().findAllActions();
    }

    @Override
    public ActionSummarized findById(Long id) {
        return repositoryFactory.getActionRepository().findById(id)
                .map(this::summarizedAction)
                .orElseThrow(() -> new HttpNotFoundException("Action not found"));
    }

    @Override
    public ActionSummarized save(RestAction restAction) {

        if (restAction.getId() != null) {
            var foundAction = repositoryFactory.getActionRepository().findById(restAction.getId())
                    .orElseThrow(() -> new HttpBadRequestException("Action not found"));

            foundAction.setComment(restAction.getComment());
            foundAction.setLiked(restAction.getLiked());

            if (restAction.getUserId() != null) {
                var foundUser = repositoryFactory.getUserRepository().findById(restAction.getUserId())
                        .orElseThrow(() -> new HttpNotFoundException("User not found"));
                foundAction.setUser(foundUser);
            }

            if (restAction.getProjectId() != null) {
                var foundProject = repositoryFactory.getProjectRepository().findById(restAction.getProjectId())
                        .orElseThrow(() -> new HttpNotFoundException("Project not found"));
                foundAction.setProject(foundProject);
            }

            var savedAction = repositoryFactory.getActionRepository().save(foundAction);
            return summarizedAction(savedAction);
        } else {
            var action = Action.builder()
                    .comment(restAction.getComment())
                    .liked(restAction.getLiked())
                    .project(repositoryFactory.getProjectRepository().findById(restAction.getProjectId())
                            .orElseThrow(() -> new HttpNotFoundException("Project not found")))
                    .user(repositoryFactory.getUserRepository().findById(restAction.getUserId())
                            .orElseThrow(() -> new HttpNotFoundException("User not found")))
                    .build();

            var savedAction = repositoryFactory.getActionRepository().save(action);
            return summarizedAction(savedAction);
        }
    }


    @Override
    public void deleteById(Long id) {
        repositoryFactory.getActionRepository().deleteById(id);
    }


    private ActionSummarized summarizedAction(Action action) {
        return new ActionSummarized(
                action.getId(),
                action.getLiked(),
                action.getComment(),
                action.getProject().getId(),
                action.getUser().getId(),
                action.getCreatedAt(),
                action.getUpdatedAt()
        );
    }

}
