package com.best.of.ngah.bionumtech.dtos.actions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestAction {
    private Long id;

    @NotNull(message = "Liked count cannot be null")
    private Long liked;

    @Size(max = 255, message = "Comment must be less than 255 characters")
    private String comment;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
