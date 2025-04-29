package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegisterUserAdapter implements RegisterUserPort {

    private final UserJpaRepository userRepository;

    @Override
    public void register(String name, String surname, String email, String password, String role) {
        try {
            UserRole userRole = UserRole.valueOf(role);
            UserEntity user = new UserEntity(null, name, surname, email, password, userRole);

            UserJpaEntity userJpa = UserEntityMapper.toJpaEntity(user);
            userRepository.save(userJpa);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException("Já existe um usuário cadastrado com o e-mail: " + email);
        }
    }
}


