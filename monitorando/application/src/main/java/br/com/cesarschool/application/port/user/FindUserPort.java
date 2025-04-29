package br.com.cesarschool.application.port.user;

import java.util.Optional;

public interface FindUserPort<T> {
    Optional<T> findByEmail(String email);
}
