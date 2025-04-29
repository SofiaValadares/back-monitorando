package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegisterUserAdapter implements RegisterUserPort {

    private final UserRepository userRepository;

    @Override
    public void register(String name, String surname, String email, String password, String role) {
        UserRole userRole = UserRole.fromString(role);
        userRepository.save(new UserEntity(null, name, surname, email, password, userRole));
    }
}
