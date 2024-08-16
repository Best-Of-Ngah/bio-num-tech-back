package com.best.of.ngah.bionumtech.dtos.projects;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
sealed abstract class ProjectBase permits CreateProject, ProjectSummarized, ProjectWithTypeSummarized, UpdateProject {

    @NotNull(message = "Status is required")
    private Boolean status;

    @NotNull(message = "Budget is required")
    @Min(value = 0, message = "Budget must be a positive number")
    private Double budget;

    @NotBlank(message = "Description is required and cannot be empty")
    private String description;

    @NotNull(message = "Request Date is required")
    private LocalDateTime requestDate;

    private LocalDateTime realisationDate;
}
