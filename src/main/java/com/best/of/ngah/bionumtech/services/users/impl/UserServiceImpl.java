package com.best.of.ngah.bionumtech.services.users.impl;

import com.best.of.ngah.bionumtech.dtos.pagination.PageInfo;
import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.dtos.users.UpdateUser;
import com.best.of.ngah.bionumtech.dtos.users.UserTemplate;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.files.FileService;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import com.best.of.ngah.bionumtech.services.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RepositoryFactory repository;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows(UsernameNotFoundException.class)
    public UserDetails loadUserByUsername(String username) {
        var user = repository.getUserRepository().findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }


    @Override
    public Paginate<List<UserTemplate>> findAllUsers(Integer pageSize, Integer currentPage) {
        var page = PageRequest.of(pageSize - 1, currentPage);
        var users = repository.getUserRepository().findAllUsers(page);
        var items = users.getContent();
        var pageInfo = new PageInfo(users.hasNext(), users.hasPrevious());
        var totalPage = users.getTotalPages();
        return new Paginate<>(items, pageInfo, totalPage);
    }

    @Override
    public UserTemplate updateUser(UpdateUser newUser) {
        var foundUser = repository.getUserRepository().findById(newUser.getId())
                .orElseThrow(() -> new HttpNotFoundException("user not found"));

        var imageUrl = foundUser.getImage();
        if (imageUrl != null) {
            fileService.deleteFile(imageUrl);
        }
        var newUrl = fileService.saveFile(newUser.getImage());
        foundUser.setImage(newUrl);
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            foundUser.setEmail(newUser.getEmail());
        }
        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            foundUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        var savedUser = repository.getUserRepository().save(foundUser);
        return new UserTemplate(savedUser.getId(), savedUser.getEmail(), savedUser.getImage());
    }

    @Override
    public UserTemplate findById(String id) {
        var foundUser = repository.getUserRepository().findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpNotFoundException("user not found"));

        return new UserTemplate(foundUser.getId(), foundUser.getEmail(), foundUser.getImage());
    }

    @Override
    public Paginate<List<UserTemplate>> getUserByParameters(String keyword, Integer pageSize, Integer currentPage) {
        var page = PageRequest.of(currentPage - 1, pageSize);
        var users = repository.getUserRepository().findByEmailContaining(keyword, page);
        var items = users.getContent();
        var pageInfo = new PageInfo(users.hasNext(), users.hasPrevious());
        var totalPage = users.getTotalPages();
        return new Paginate<>(items, pageInfo, totalPage);
    }

    @Override
    public void deleteUser(Long id) {
        if (repository.getUserRepository().existsById(id)) {
            repository.getUserRepository().deleteById(id);
            return;
        }
        throw new HttpNotFoundException("User not found");
    }
}
