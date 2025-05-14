package br.com.cesarschool.application.port.user;

import java.util.Optional;

public interface FindStudentPort<T> {
    Optional<T> findById(Long id);
}
