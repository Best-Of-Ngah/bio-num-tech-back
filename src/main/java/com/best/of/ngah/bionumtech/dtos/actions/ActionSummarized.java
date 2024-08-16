package com.best.of.ngah.bionumtech.dtos.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ActionSummarized {
    private Long id;
    private Long liked;
    private String comment;
    private Long projectId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
