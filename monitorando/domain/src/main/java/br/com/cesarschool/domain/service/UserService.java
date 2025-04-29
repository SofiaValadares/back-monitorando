package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.user.FindUserPort;
import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RegisterUserPort registerUserPort;
    private final FindUserPort<UserEntity> findUserPort;
    private final LoginUserPort<UserEntity> loginUserPort;

    public void register(String name, String surname, String email, String password, String role) {
        Optional<UserEntity> userOptional = findUserPort.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new EmailAlreadyInUseException("Já existe um usuário cadastrado com o e-mail: " + email);
        }

        registerUserPort.register(name, surname, email, password, role);
    }

    public UserEntity login(String email, String password) {
        return loginUserPort.loginUser(email, password);
    }

}

