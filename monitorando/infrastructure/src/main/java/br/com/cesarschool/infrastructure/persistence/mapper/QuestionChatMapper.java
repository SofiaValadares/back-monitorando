package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.QuestionChatEntity;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionChatJpaEntity;

public class QuestionChatMapper {

    public static QuestionChatEntity toDomain(QuestionChatJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;
        return new QuestionChatEntity(
                jpaEntity.getId(),
                jpaEntity.getQuestion().getId(),
                jpaEntity.getUser().getId(),
                jpaEntity.getAnswer()
        );
    }

    public static QuestionChatJpaEntity toJpa(QuestionChatEntity domainEntity) {

        if (domainEntity == null) return null;
        return new QuestionChatJpaEntity(
                domainEntity.getId(),
                null,
                null,
                domainEntity.getAnswer()
        );
    }
}
