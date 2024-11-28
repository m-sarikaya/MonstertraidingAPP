package org.example.application.repository;

import org.example.application.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMemoryRepository implements UserRepository {

    private Map<String, UserEntity> userStore = new HashMap<>();

    @Override
    public boolean existsByUsername(String username) {
        return userStore.containsKey(username);
    }

    @Override
    public UserEntity save(UserEntity user) {
        userStore.put(user.getFirstName(), user);
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userStore.get(username);
    }

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(userStore.values());
    }
}