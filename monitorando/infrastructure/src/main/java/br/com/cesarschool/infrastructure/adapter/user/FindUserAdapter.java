package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.FindUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUserAdapter implements FindUserPort<UserEntity> {

    private final UserJpaRepository userRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserEntityMapper::toDomainEntity);
    }
}
