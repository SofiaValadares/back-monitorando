package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.repository.user.FindUserRepository;
import br.com.cesarschool.domain.repository.user.LoginUserRepository;
import br.com.cesarschool.domain.repository.user.PromoteUserToStudentRepository;
import br.com.cesarschool.domain.repository.user.RegisterUserRepository;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final RegisterUserRepository registerUserRepository;
    private final PromoteUserToStudentRepository promoteUserToStudentRepository;
    private final FindUserRepository<UserEntity> findUserRepository;
    private final LoginUserRepository<UserEntity> loginUserRepository;

    public UserService(RegisterUserRepository registerUserRepository, PromoteUserToStudentRepository promoteUserToStudentRepository, FindUserRepository<UserEntity> findUserRepository, LoginUserRepository<UserEntity> loginUserRepository) {
        this.registerUserRepository = registerUserRepository;
        this.promoteUserToStudentRepository = promoteUserToStudentRepository;
        this.findUserRepository = findUserRepository;
        this.loginUserRepository = loginUserRepository;
    }

    public void register(String name, String surname, String email, String password, String role) {
        Optional<UserEntity> userOptional = findUserRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new EmailAlreadyInUseException("Já existe um usuário cadastrado com o e-mail: " + email);
        }

        registerUserRepository.register(name, surname, email, password, role);

        if ("STUDENT".equalsIgnoreCase(role)) {
            Optional<UserEntity> newUser = findUserRepository.findByEmail(email);
            newUser.ifPresent(user -> promoteUserToStudentRepository.promote(user.getId()));
        }
    }

    public UserEntity login(String email, String password) {
        return loginUserRepository.loginUser(email, password);
    }

}

