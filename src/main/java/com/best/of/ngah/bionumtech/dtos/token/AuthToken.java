package com.best.of.ngah.bionumtech.dtos.token;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Setter
public class AuthToken implements Serializable {
    private String type;
    private String token;
}
