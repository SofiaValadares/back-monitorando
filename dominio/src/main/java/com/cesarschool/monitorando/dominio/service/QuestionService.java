package com.cesarschool.monitorando.dominio.service;

import com.cesarschool.monitorando.apresentacao.DTO.QuestionRequestDTO;
import com.cesarschool.monitorando.dominio.entity.DisciplineEntity;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import com.cesarschool.monitorando.dominio.entity.QuestionEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import com.cesarschool.monitorando.persistencia.repository.DisciplineRepository;
import com.cesarschool.monitorando.persistencia.repository.MonitorRepository;
import com.cesarschool.monitorando.persistencia.repository.QuestionRepository;
import com.cesarschool.monitorando.persistencia.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService {

	@Autowired private QuestionRepository questionRepository;
	@Autowired private StudentRepository studentRepository;
	@Autowired private MonitorRepository monitorRepository;
	@Autowired private DisciplineRepository disciplineRepository;

    @Transactional
    public String createQuestion(QuestionRequestDTO request) {
        // Validação: monitor não selecionado
        if (request.getMonitorId() == null) {
            return "Erro: É necessário selecionar um monitor.";
        }

        // Validação: mensagem vazia
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            return "Erro: A mensagem da dúvida é obrigatória.";
        }

        // Buscar entidades relacionadas
        StudentEntity student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        MonitorEntity monitor = monitorRepository.findById(request.getMonitorId())
                .orElseThrow(() -> new RuntimeException("Monitor não encontrado"));
        DisciplineEntity discipline = disciplineRepository.findById(request.getDisciplineId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        // Criar nova questão
        QuestionEntity question = new QuestionEntity();
        question.setStudent(student);
        question.setMonitor(monitor); // novo campo que adicionamos
        question.setDiscipline(discipline);
        question.setTitle(request.getTitle());
        question.setContent(request.getContent());
        question.setIsPublic(request.getIsPublic());
        question.setStatus(QuestionEntity.Status.open);
        question.setCreatedAt(LocalDateTime.now());

        questionRepository.save(question);

        // Notificação simulada
        return "Dúvida enviada com sucesso!";
    }
}
