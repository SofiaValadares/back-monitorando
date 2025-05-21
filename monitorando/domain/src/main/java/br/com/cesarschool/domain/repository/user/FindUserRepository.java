package br.com.cesarschool.domain.repository.user;

import java.util.Optional;

public interface FindUserRepository<T> {
    Optional<T> findByEmail(String email);
    Optional<T> findById(Long id);
}
