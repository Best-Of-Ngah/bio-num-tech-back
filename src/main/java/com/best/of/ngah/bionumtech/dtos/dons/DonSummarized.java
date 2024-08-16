package com.best.of.ngah.bionumtech.dtos.dons;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DonSummarized {

    private Long id;

    private String provenance;

    private LocalDate provenanceDate;

    private Double amount;

    private Long userId;

    private Long projectId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
