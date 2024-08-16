package com.best.of.ngah.bionumtech.services.users;

import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.users.UpdateUser;
import com.best.of.ngah.bionumtech.dtos.users.UserTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Paginate<List<UserTemplate>> findAllUsers(Integer pageSize, Integer currentPage);

    Optional<UserTemplate> updateUser(UpdateUser newUser);

    Optional<UserTemplate> findById(String id);

    Paginate<List<UserTemplate>> getUserByParameters(String keyword, Integer pageSize, Integer currentPage);

}
