package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.repository.user.FindStudentRepository;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.StudentMapper;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindStundentAdapter implements FindStudentRepository<StudentEntity> {
    StudentJpaRepository studentJpaRepository;

    @Override
    public Optional<StudentEntity> findById(Long id) {
        Optional<StudentJpaEntity> studentOptional = studentJpaRepository.findById(id);

        if (studentOptional.isPresent()) {
            StudentJpaEntity studentJpa = studentOptional.get();
            return Optional.of(StudentMapper.toDomain(studentJpa));
        }

        return Optional.empty();
    }
}

