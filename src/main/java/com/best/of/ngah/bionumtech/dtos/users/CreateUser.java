package com.best.of.ngah.bionumtech.dtos.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public final class CreateUser extends UserBase implements Serializable {
    private MultipartFile file;
    public CreateUser(
            String email,
            String password,
            MultipartFile file
    ) {
        super(email, password);
        this.file = file;
    }
}
