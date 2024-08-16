package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized;
import com.best.of.ngah.bionumtech.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {


    @Query("""
            SELECT
                NEW com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized(a.id, a.liked, a.comment, a.project.id, a.user.id, a.createdAt, a.updatedAt)
             FROM Action a
            """)
    List<ActionSummarized> findAllActions();
}
