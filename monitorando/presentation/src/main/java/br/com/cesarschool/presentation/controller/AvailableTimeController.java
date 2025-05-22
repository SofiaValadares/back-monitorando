package br.com.cesarschool.presentation.controller;


import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.service.AvailableTimeService;
import br.com.cesarschool.presentation.dto.user.AvailableTimeRequestDTO;
import br.com.cesarschool.presentation.dto.user.AvailableTimeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitores")
@RequiredArgsConstructor
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;


    // POST /monitores/{id}/horarios
    @PostMapping("/{monitorId}/horarios")
    public ResponseEntity<AvailableTimeResponseDTO> criarHorario(
            @PathVariable Long monitorId,
            @RequestBody AvailableTimeRequestDTO dto) {

        AvailableTimeEntity entity = new AvailableTimeEntity(
                null,
                dto.getWeekDay(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        AvailableTimeEntity salvo = availableTimeService.defineAvailableTime(entity, monitorId);

        AvailableTimeResponseDTO resposta = new AvailableTimeResponseDTO();
        resposta.setId(salvo.getId());
        resposta.setWeekDay(salvo.getWeekDay());
        resposta.setStartTime(salvo.getStartTime());
        resposta.setEndTime(salvo.getEndTime());

        return ResponseEntity.ok(resposta);
    }

    // GET /monitores/{id}/horarios
    @GetMapping("/{monitorId}/horarios")
    public ResponseEntity<List<AvailableTimeResponseDTO>> listarHorarios(
            @PathVariable Long monitorId) {

        List<AvailableTimeEntity> lista = availableTimeService.getAvailableTimesByMonitor(monitorId);

        List<AvailableTimeResponseDTO> resposta = lista.stream()
                .map(entity -> {
                    AvailableTimeResponseDTO dto = new AvailableTimeResponseDTO();
                    dto.setId(entity.getId());
                    dto.setWeekDay(entity.getWeekDay());
                    dto.setStartTime(entity.getStartTime());
                    dto.setEndTime(entity.getEndTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }

    // DELETE /horarios/{id}
    @DeleteMapping("/horarios/{horarioId}")
    public ResponseEntity<Void> deletarHorario(@PathVariable Long horarioId) {
        availableTimeService.removeAvailableTimeById(horarioId);
        return ResponseEntity.noContent().build();
    }
}
