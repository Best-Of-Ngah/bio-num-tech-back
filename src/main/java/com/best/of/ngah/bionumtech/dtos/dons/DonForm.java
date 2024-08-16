package com.best.of.ngah.bionumtech.dtos.dons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
public class DonForm {

    private Long id;

    @NotBlank(message = "Provenance cannot be blank")
    private String provenance;

    @NotNull(message = "Provenance Date cannot be null")
    private LocalDate provenanceDate;

    @PositiveOrZero(message = "Amount must be positive or zero")
    private Double amount;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
}
