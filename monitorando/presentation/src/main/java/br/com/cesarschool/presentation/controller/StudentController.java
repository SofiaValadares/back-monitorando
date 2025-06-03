package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.service.StudentService;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.presentation.dto.disciplina.DisciplineResponseDTO;
import br.com.cesarschool.presentation.dto.user.StudentAddDisciplineRequest;
import br.com.cesarschool.presentation.dto.user.StudentAddDisciplineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/add-discipline")
    public ResponseEntity<StudentAddDisciplineResponse> addDiscipline(@RequestBody StudentAddDisciplineRequest request) {
        studentService.addDiscipline(request.idStudent(), request.codeDiscipline());
        return ResponseEntity.ok(new StudentAddDisciplineResponse("Disciplina adicionada com sucesso"));
    }

    @GetMapping("/count-discipline/{id}")
    public ResponseEntity<Integer> getCountDiscipline(@PathVariable Long id) {
        Integer count = studentService.countDisciplines(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<List<DisciplineResponseDTO>> getDisciplines(@PathVariable Long id) {
        List<DisciplineEntity> disciplineEntities = studentService.getDisciplines(id);

        List<DisciplineResponseDTO> disciplineResponseDTOS = disciplineEntities.stream()
                .map(d -> new DisciplineResponseDTO(d.getId(), d.getName(), d.getCode()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(disciplineResponseDTOS);
    }

}
