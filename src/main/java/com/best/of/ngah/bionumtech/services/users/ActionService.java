package com.best.of.ngah.bionumtech.services.users;

import com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized;
import com.best.of.ngah.bionumtech.dtos.actions.RestAction;

import java.util.List;

public interface ActionService {
    List<ActionSummarized> findAll();

    ActionSummarized findById(Long id);

    ActionSummarized save(RestAction action);

    void deleteById(Long id);
}
