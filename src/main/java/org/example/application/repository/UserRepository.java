package org.example.application.repository;

import org.example.application.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByUsername(String username);

    UserEntity save(UserEntity user);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();
}
