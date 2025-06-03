package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.repository.user.PromoteUserToStudentRepository;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.repository.DisciplineJpaRepository;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PromoteUserToStudentAdapter implements PromoteUserToStudentRepository {

    private final UserJpaRepository userRepository;
    private final StudentJpaRepository studentRepository;
    private final MonitorJpaRepository monitorRepository;
    private final DisciplineJpaRepository disciplineRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public PromoteUserToStudentAdapter(UserJpaRepository userRepository, StudentJpaRepository studentRepository, MonitorJpaRepository monitorRepository, DisciplineJpaRepository disciplineRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.monitorRepository = monitorRepository;
        this.disciplineRepository = disciplineRepository;
        this.entityManager = entityManager;
    }


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


    @Override
    @Transactional
    public void promoteMonitor(Long userId, Long disciplineId) {
        if (monitorRepository.existsById(userId)) {
            return;
        }

        StudentJpaEntity student = studentRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DisciplineJpaEntity discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new RuntimeException("Disciplina nao encontrada"));

        MonitorJpaEntity monitor = new MonitorJpaEntity(userId, student,discipline, null);

        monitorRepository.save(monitor);

    }
}
