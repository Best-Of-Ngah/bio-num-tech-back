package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized;
import com.best.of.ngah.bionumtech.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("""
            SELECT
                 new com.best.of.ngah.bionumtech.dtos.projects.ProjectSummarized(
                     p.id, p.status,p.budget, p.description, p.requestDate, p.realisationDate,p.image, p.createdAt, p.updatedAt
                 )
            FROM Project p
            """)
    Page<ProjectSummarized> findAllProjects(Pageable pageable);
}
