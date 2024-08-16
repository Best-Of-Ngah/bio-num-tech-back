package com.best.of.ngah.bionumtech.dtos.projects;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public final class ProjectSummarized extends ProjectBase {
    private Long id;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProjectSummarized(
            Long id,
            Boolean status,
            Double budget,
            String description,
            LocalDateTime requestDate,
            LocalDateTime realisationDate,
            String image,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(status, budget, description, requestDate, realisationDate);
        this.id = id;
        this.image = image;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
