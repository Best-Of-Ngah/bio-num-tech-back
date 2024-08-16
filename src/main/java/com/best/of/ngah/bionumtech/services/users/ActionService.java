package com.best.of.ngah.bionumtech.services.users;

import com.best.of.ngah.bionumtech.entities.Action;

import java.util.List;
import java.util.Optional;

public interface ActionService {
    List<Action> findAll();

    Optional<Action> findById(Long id);

    Action register(Action action);

    void deleteById(Long id);
}
