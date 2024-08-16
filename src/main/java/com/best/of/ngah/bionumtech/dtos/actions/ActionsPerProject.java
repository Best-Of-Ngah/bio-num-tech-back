package com.best.of.ngah.bionumtech.dtos.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ActionsPerProject {
    private Long projectId;
    private Long totalLinked;
}
