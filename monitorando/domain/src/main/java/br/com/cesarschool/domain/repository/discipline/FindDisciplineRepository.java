package br.com.cesarschool.domain.repository.discipline;

import br.com.cesarschool.domain.entity.DisciplineEntity;

import java.util.Optional;

public interface FindDisciplineRepository {
    Optional<DisciplineEntity> findById(Long id);
    Optional<DisciplineEntity> findByCode(String code);
}
