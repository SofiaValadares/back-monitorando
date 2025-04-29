package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserAdapter implements LoginUserPort<UserEntity> {

    private final UserRepository userRepository;

    @Override
    public UserEntity loginUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o e-mail: " + email));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return user;
    }
}
