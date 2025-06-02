package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.LoginIncorrectException;
import br.com.cesarschool.domain.repository.user.LoginUserRepository;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginUserAdapter implements LoginUserRepository<UserEntity> {
    private final UserJpaRepository userRepository;

    public LoginUserAdapter(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity loginUser(String email, String password) {
        UserJpaEntity userJPA = userRepository.findByEmail(email)
            .orElseThrow(LoginIncorrectException::new);

        UserEntity user = UserEntityMapper.toDomainEntity(userJPA);

        if (!user.getPassword().equals(password)) {
            throw new LoginIncorrectException();
        }

        userJPA.setActive(true);
        userRepository.save(userJPA);

        return user;
    }

    @Override
    public Boolean hasLoginUser(Long id) {
        UserJpaEntity userJPA = userRepository.findById(id).orElse(null);

        if (userJPA == null) {
            return false;
        }

        return userJPA.getActive();
    }

    @Override
    public void logoutUser(Long id) {
        UserJpaEntity userJPA = userRepository.findById(id).orElse(null);

        if (userJPA == null) {
            throw new LoginIncorrectException();
        }

        userJPA.setActive(false);

        userRepository.save(userJPA);
    }
}
