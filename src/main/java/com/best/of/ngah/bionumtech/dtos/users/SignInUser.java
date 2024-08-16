package com.best.of.ngah.bionumtech.dtos.users;


import java.io.Serializable;

public final class SignInUser extends UserBase implements Serializable {
    public SignInUser(String email, String password) {
        super(email, password);
    }
}
