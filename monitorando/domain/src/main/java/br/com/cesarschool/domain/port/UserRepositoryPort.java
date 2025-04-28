package br.com.cesarschool.domain.port;

import br.com.cesarschool.domain.entity.UserEntity;

public interface UserRepositoryPort {
    UserEntity save(UserEntity user);
}
