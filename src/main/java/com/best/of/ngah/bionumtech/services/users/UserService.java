package com.best.of.ngah.bionumtech.services.users;

import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.users.UpdateUser;
import com.best.of.ngah.bionumtech.dtos.users.UserTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Paginate<List<UserTemplate>> findAllUsers(Integer pageSize, Integer currentPage);

    UserTemplate updateUser(UpdateUser newUser);

    UserTemplate findById(String id);

    Paginate<List<UserTemplate>> getUserByParameters(String keyword, Integer pageSize, Integer currentPage);

    void deleteUser(Long id);

}
