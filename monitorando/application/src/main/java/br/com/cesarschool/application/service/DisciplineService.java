package br.com.cesarschool.application.service;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.global.RandomStringGenerator;
import br.com.cesarschool.domain.repository.discipline.CreateDisciplineRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {
    private final CreateDisciplineRepository createDisciplineRepository;


    public DisciplineService(CreateDisciplineRepository createDisciplineRepository) {
        this.createDisciplineRepository = createDisciplineRepository;
    }

    public DisciplineEntity disciplineCreate(String disciplineName) {
        String disciplineCode = RandomStringGenerator.generateUniqueString(8);
        return createDisciplineRepository.createDiscipline(disciplineName, disciplineCode);
    }


}
