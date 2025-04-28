package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.service.UserService;
import br.com.cesarschool.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegisterUserAdapter implements RegisterUserPort {

    private final UserRepository userRepository;

    @Override
    public void register(String name, String email, String password) {
        userRepository.save(new UserEntity(null, name, email, password));
    }
}
