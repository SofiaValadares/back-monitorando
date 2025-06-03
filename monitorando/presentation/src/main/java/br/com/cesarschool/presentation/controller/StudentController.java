package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.service.StudentService;
import br.com.cesarschool.presentation.dto.user.StudentAddDisciplineRequest;
import br.com.cesarschool.presentation.dto.user.StudentAddDisciplineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
