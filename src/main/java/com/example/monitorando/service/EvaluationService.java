package com.example.monitorando.service;

import com.example.monitorando.DTO.EvaluationRequestDTO;
import com.example.monitorando.entity.AppointmentEntity;
import com.example.monitorando.entity.EvaluationEntity;
import com.example.monitorando.entity.QuestionEntity;
import com.example.monitorando.entity.UserEntity;
import com.example.monitorando.repository.AppointmentRepository;
import com.example.monitorando.repository.EvaluationRepository;
import com.example.monitorando.repository.QuestionRepository;
import com.example.monitorando.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    @Autowired private EvaluationRepository evaluationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private QuestionRepository questionRepository;

    @Transactional
    public EvaluationEntity avaliar(@Valid EvaluationRequestDTO dto) {
        UserEntity evaluator = userRepository.findById(dto.getEvaluatorId())
                .orElseThrow(() -> new IllegalArgumentException("Avaliador não encontrado"));

        UserEntity evaluated = userRepository.findById(dto.getEvaluatedId())
                .orElseThrow(() -> new IllegalArgumentException("Avaliado não encontrado"));

        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setEvaluator(evaluator);
        evaluation.setEvaluated(evaluated);
        evaluation.setRating(dto.getRating());
        evaluation.setComment(dto.getComment());

        if (dto.getAppointmentId() != null) {
            AppointmentEntity appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado"));
            evaluation.setAppointment(appointment);
        }

        if (dto.getQuestionId() != null) {
            QuestionEntity question = questionRepository.findById(dto.getQuestionId())
                    .orElseThrow(() -> new IllegalArgumentException("Dúvida não encontrada"));
            evaluation.setQuestion(question);
        }

        if (dto.getAppointmentId() == null && dto.getQuestionId() == null) {
            throw new IllegalArgumentException("É necessário informar appointmentId ou questionId.");
        }

        return evaluationRepository.save(evaluation);
    }
}
