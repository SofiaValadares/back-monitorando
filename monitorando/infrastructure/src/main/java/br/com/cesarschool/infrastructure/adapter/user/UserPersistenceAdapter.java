package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.FindUserPort;
import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements RegisterUserPort, FindUserPort<UserEntity>, LoginUserPort<UserEntity> {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void register(String name, String surname, String email, String password, String role) {
        try {
            UserEntity domainUser = new UserEntity(null, name, surname, email, password, Enum.valueOf(br.com.cesarschool.domain.entity.enums.UserRole.class, role));
            UserJpaEntity jpaUser = UserEntityMapper.toJpaEntity(domainUser);
            userJpaRepository.save(jpaUser);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException("Já existe um usuário cadastrado com o e-mail: " + email);
        }
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserEntityMapper::toDomainEntity);
    }

    @Override
    public UserEntity loginUser(String email, String password) {
        return userJpaRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(UserEntityMapper::toDomainEntity)
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }
}
