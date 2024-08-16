package com.best.of.ngah.bionumtech.dtos.projects;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public final class CreateProject extends ProjectBase {
    @NotNull(message = "ProjectType is required")
    private Long typeId;

    @NotNull(message = "User is required")
    private Long userId;

    private MultipartFile file;

    public CreateProject(
            Long typeId,
            Long userId,
            Boolean status,
            Double budget,
            String description,
            LocalDateTime requestDate,
            LocalDateTime realisationDate,
            MultipartFile file
    ) {
        super(status, budget, description, requestDate, realisationDate);
        this.typeId = typeId;
        this.file = file;
        this.userId = userId;
    }
}
