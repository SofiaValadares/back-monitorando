package br.com.cesarschool.presentation.controller;


import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.service.DisciplineService;
import br.com.cesarschool.presentation.dto.disciplina.DisciplineCreateRequestDTO;
import br.com.cesarschool.presentation.dto.disciplina.DisciplineCreateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostMapping("/create")
    public ResponseEntity<DisciplineCreateResponseDTO> create(@RequestBody DisciplineCreateRequestDTO disciplineCreateRequestDTO) {
        DisciplineEntity discipline = disciplineService.disciplineCreate(disciplineCreateRequestDTO.name());

        DisciplineCreateResponseDTO response = new DisciplineCreateResponseDTO(
                discipline.getId(),
                discipline.getName(),
                discipline.getCode()
        );

        return ResponseEntity.ok(response);
    }
}
