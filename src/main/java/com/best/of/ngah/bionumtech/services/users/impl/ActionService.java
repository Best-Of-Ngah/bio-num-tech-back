package com.best.of.ngah.bionumtech.services.users.impl;

import com.best.of.ngah.bionumtech.entities.Action;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionService implements com.best.of.ngah.bionumtech.services.users.ActionService {
    private final RepositoryFactory repositoryFactory;

    @Override
    public List<Action> findAll() {
        return repositoryFactory.getActionRepository().findAll();
    }

    @Override
    public Optional<Action> findById(Long id) {
        return repositoryFactory.getActionRepository().findById(id);
    }

    @Override
    public Action register(Action action) {
        return repositoryFactory.getActionRepository().save(action);
    }

    @Override
    public void deleteById(Long id) {
        repositoryFactory.getActionRepository().deleteById(id);
    }
}
