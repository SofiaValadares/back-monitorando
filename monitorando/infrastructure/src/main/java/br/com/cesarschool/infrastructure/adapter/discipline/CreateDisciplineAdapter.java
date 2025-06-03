package br.com.cesarschool.infrastructure.adapter.discipline;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.repository.discipline.CreateDisciplineRepository;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.DisciplineMapper;
import br.com.cesarschool.infrastructure.repository.DisciplineJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateDisciplineAdapter implements CreateDisciplineRepository {
    private final DisciplineJpaRepository disciplineJpaRepository;

    public CreateDisciplineAdapter(DisciplineJpaRepository disciplineJpaRepository) {
        this.disciplineJpaRepository = disciplineJpaRepository;
    }

    @Override
    public DisciplineEntity createDiscipline(String disciplineName, String disciplineCode) {
        DisciplineJpaEntity disciplineJpa = new DisciplineJpaEntity(disciplineName, disciplineCode);


        return DisciplineMapper.toDomain(
                disciplineJpaRepository.save(disciplineJpa)
        );
    }
}
