package br.com.cesarschool.domain.repository.discipline;

import java.util.Optional;

public interface FindDisciplineRepository<T> {
    Optional<T> findById(Long id);
}
