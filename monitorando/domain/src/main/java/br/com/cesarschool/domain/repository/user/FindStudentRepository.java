package br.com.cesarschool.domain.repository.user;

import br.com.cesarschool.domain.entity.StudentEntity;

import java.util.Optional;

public interface FindStudentRepository {
    Optional<StudentEntity> findById(Long id);
}
