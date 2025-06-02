package br.com.cesarschool.domain.repository.user;

import java.util.Optional;

public interface FindMonitorRepository<T> {
    Optional<T> findById(Long id);
}
