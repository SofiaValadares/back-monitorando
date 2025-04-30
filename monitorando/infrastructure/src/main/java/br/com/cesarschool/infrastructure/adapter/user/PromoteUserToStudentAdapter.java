package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.PromoteUserToStudentPort;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromoteUserToStudentAdapter implements PromoteUserToStudentPort {

    private final UserJpaRepository userRepository;
    private final StudentJpaRepository studentRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void promote(Long userId) {
        if (studentRepository.existsById(userId)) {
            return;
        }

        UserJpaEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        StudentJpaEntity student = new StudentJpaEntity(user.getId(), user, List.of());

        entityManager.persist(student);
    }
}
