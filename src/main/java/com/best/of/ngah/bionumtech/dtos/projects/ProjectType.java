package com.best.of.ngah.bionumtech.dtos.projects;

import com.best.of.ngah.bionumtech.enums.TypeName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProjectType implements Serializable {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private TypeName name;
}
