package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.dtos.users.UserTemplate;
import com.best.of.ngah.bionumtech.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(""" 
            SELECT NEW com.best.of.ngah.bionumtech.dtos.users.UserTemplate(
            u.id ,u.email,u.image
            ) FROM User u
            """)
    Page<UserTemplate> findAllUsers(Pageable pageable);

}
