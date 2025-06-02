package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;

public class UserEntityMapper {

    public static UserJpaEntity toJpaEntity(UserEntity userEntity) {
        return new UserJpaEntity(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getActive()
        );
    }

    public static UserEntity toDomainEntity(UserJpaEntity userJpaEntity) {
        return new UserEntity(
                userJpaEntity.getId(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname(),
                userJpaEntity.getEmail(),
                userJpaEntity.getPassword(),
                userJpaEntity.getRole(),
                userJpaEntity.getActive()
        );
    }
}
