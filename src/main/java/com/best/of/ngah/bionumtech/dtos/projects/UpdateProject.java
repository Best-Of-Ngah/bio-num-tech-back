package com.best.of.ngah.bionumtech.dtos.projects;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public final class UpdateProject extends ProjectBase implements Serializable {
    @NotNull(message = "Project ID is required")
    private Long id;
    @NotNull(message = "ProjectTypeId is required")
    private Long typeId;

    @NotNull(message = "UserId is required")
    private Long userId;

    private MultipartFile file;

    public UpdateProject(
            Long id,
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
        this.id = id;
        this.typeId = typeId;
        this.userId = userId;
        this.file = file;
    }
}
