package br.com.cesarschool.domain.repository.discipline;

import br.com.cesarschool.domain.entity.DisciplineEntity;

public interface CreateDisciplineRepository {
    DisciplineEntity createDiscipline(String disciplineName, String disciplineCode);
}
