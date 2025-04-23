package com.cesarschool.monitorando.apresentacao.service;

import com.cesarschool.monitorando.apresentacao.DTO.EvaluationRequestDTO;
import com.cesarschool.monitorando.dominio.entity.AppointmentEntity;
import com.cesarschool.monitorando.dominio.entity.EvaluationEntity;
import com.cesarschool.monitorando.dominio.entity.QuestionEntity;
import com.cesarschool.monitorando.dominio.entity.UserEntity;
import com.cesarschool.monitorando.persistencia.repository.AppointmentRepository;
import com.cesarschool.monitorando.persistencia.repository.EvaluationRepository;
import com.cesarschool.monitorando.persistencia.repository.QuestionRepository;
import com.cesarschool.monitorando.persistencia.repository.UserRepository;
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
