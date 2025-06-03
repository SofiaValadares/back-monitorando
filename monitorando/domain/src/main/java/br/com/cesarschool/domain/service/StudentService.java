package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.domain.repository.user.FindStudentRepository;
import br.com.cesarschool.domain.repository.user.StudentAddDisciplineRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final FindStudentRepository findStudentRepository;
    private final StudentAddDisciplineRepository studentAddDisciplineRepository;
    private final FindDisciplineRepository findDisciplineRepository;


    public StudentService(FindStudentRepository findStudentRepository, StudentAddDisciplineRepository studentAddDisciplineRepository, FindDisciplineRepository findDisciplineRepository) {
        this.findStudentRepository = findStudentRepository;
        this.studentAddDisciplineRepository = studentAddDisciplineRepository;
        this.findDisciplineRepository = findDisciplineRepository;
    }

    public void addDiscipline(Long idStudent, String codeDiscipline) {
        StudentEntity studentEntity = findStudentRepository.findById(idStudent)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + idStudent));

        DisciplineEntity discipline = findDisciplineRepository.findByCode(codeDiscipline)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with code: " + codeDiscipline));

        Long disciplineId = discipline.getId();

        if (studentEntity.getDisciplineIds().contains(disciplineId)) {
            throw new IllegalArgumentException("Discipline already exists with id: " + disciplineId + " in student with id: " + idStudent);
        }

        this.studentAddDisciplineRepository.addDiscipline(idStudent, codeDiscipline);
    }
}
