package br.com.cesarschool.infrastructure.adapter.discipline;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.DisciplineMapper;
import br.com.cesarschool.infrastructure.repository.DisciplineJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindDisciplineAdapter implements FindDisciplineRepository<DisciplineEntity> {
    private final DisciplineJpaRepository disciplineJpaRepository;
    @Override
    public Optional<DisciplineEntity> findById(Long id) {
        Optional<DisciplineJpaEntity> disciplineOptional = disciplineJpaRepository.findById(id);

        if (disciplineOptional.isPresent()) {
            DisciplineJpaEntity discipline = disciplineOptional.get();
            return Optional.of(DisciplineMapper.toDomain(discipline));
        }

        return Optional.empty();
    }
}
