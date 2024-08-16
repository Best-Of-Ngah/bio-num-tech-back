package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized;
import com.best.of.ngah.bionumtech.dtos.actions.ActionsPerProject;
import com.best.of.ngah.bionumtech.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {


    @Query("""
            SELECT
                NEW com.best.of.ngah.bionumtech.dtos.actions.ActionSummarized(a.id, a.liked, a.comment, a.project.id, a.user.id, a.createdAt, a.updatedAt)
             FROM Action a
            """)
    List<ActionSummarized> findAllActions();


    @Query("""
         SELECT
            new com.best.of.ngah.bionumtech.dtos.actions.ActionsPerProject(a.project.id, SUM(a.liked))
            FROM Action a
            WHERE
                a.project.id = :projectId
            GROUP BY
                a.project.id
""")
    Optional<ActionsPerProject> findActionsPerProjectWithCounts(@Param("projectId") Long projectId);
}
