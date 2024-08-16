package com.best.of.ngah.bionumtech.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class UserTemplate implements Serializable {
    private Long id;
    private String email;
    private String image;
}
