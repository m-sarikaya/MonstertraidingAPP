package org.example.application.services;

import org.example.application.entity.UserEntity;
import org.example.application.repository.UserRepository;
import org.example.application.repository.UserMemoryRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserMemoryRepository();
    }

    public UserEntity create(UserEntity user) {
        //Data validation
        if (userRepository.existsByUsername(user.getFirstName())) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}