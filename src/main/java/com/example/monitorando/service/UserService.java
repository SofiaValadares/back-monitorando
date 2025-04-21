package com.example.monitorando.service;

import com.example.monitorando.entity.UserEntity;
import com.example.monitorando.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
