package br.com.cesarschool.application.port.discipline;

import java.util.Optional;

public interface FindDisciplinePort<T> {
    Optional<T> findById(Long id);
}
