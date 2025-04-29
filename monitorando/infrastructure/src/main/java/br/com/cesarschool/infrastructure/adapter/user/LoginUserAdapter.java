package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.LoginIncorrectException;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserAdapter implements LoginUserPort<UserEntity> {

    private final UserJpaRepository userRepository;

    @Override
    public UserEntity loginUser(String email, String password) {
        UserJpaEntity userJPA = userRepository.findByEmail(email)
            .orElseThrow(LoginIncorrectException::new);

        UserEntity user = UserEntityMapper.toDomainEntity(userJPA);

        if (!user.getPassword().equals(password)) {
            throw new LoginIncorrectException();
        }

        return user;
    }
}
