package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RegisterUserPort registerUserPort;

    public void register(String name, String surname, String email, String password, String role) {
        UserRole userRole = UserRole.valueOf(role);

        UserEntity user = new UserEntity(null, name, surname, email, password, userRole);
        registerUserPort.register(name, surname, email, password, role);
    }
}
