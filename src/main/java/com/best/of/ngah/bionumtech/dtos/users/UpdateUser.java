package com.best.of.ngah.bionumtech.dtos.users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public final class UpdateUser extends UserBase implements Serializable {

    @NotNull(message = "ID cannot be null")
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Long id;

    private MultipartFile image;

    public UpdateUser(Long id, String email, String password, MultipartFile image) {
        super(email, password);
        this.id = id;
        this.image = image;
    }
}
