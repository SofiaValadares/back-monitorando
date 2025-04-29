package br.com.cesarschool.application.port.user;

import java.util.Optional;

public interface LoginUserPort<T> {
    Optional<T> findByEmail(String email);
}
