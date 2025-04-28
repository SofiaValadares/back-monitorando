package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RegisterUserPort registerUserPort;

    public void register(String name, String email, String password) {
        UserEntity user = new UserEntity(null, name, email, password);
        registerUserPort.register(name, email, password);
    }
}
