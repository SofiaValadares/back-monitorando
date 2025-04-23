package com.cesarschool.monitorando.dominio.service;

import com.cesarschool.monitorando.dominio.entity.UserEntity;
import com.cesarschool.monitorando.persistencia.repository.UserRepository;
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
