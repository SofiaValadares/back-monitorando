package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.repository.user.FindUserRepository;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindUserAdapter implements FindUserRepository<UserEntity> {

    private final UserJpaRepository userRepository;

    public FindUserAdapter(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserEntityMapper::toDomainEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        Optional<UserJpaEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserJpaEntity userJpaEntity = userOptional.get();
            return Optional.of(UserEntityMapper.toDomainEntity(userJpaEntity));
        }

        return Optional.empty();
    }
}
