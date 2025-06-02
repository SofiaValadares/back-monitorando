package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;


public class QuestionEntity {
    private Long id;
    private String question;
    private Long studentId;
    private Long disciplineId;
    private Boolean isPublic;
    private QuestionStatus status;
    private Long monitorId;

    public QuestionEntity(
            Long id,
            String question,
            Long studentId,
            Long disciplineId,
            Boolean isPublic,
            QuestionStatus status,
            Long monitorId
    ) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("A pergunta não pode estar vazia.");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("Pergunta deve estar associada a um aluno.");
        }
        if (disciplineId == null) {
            throw new IllegalArgumentException("Pergunta deve estar associada a uma disciplina.");
        }
        if (isPublic == null) {
            throw new IllegalArgumentException("É necessário informar se a pergunta é pública.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status da pergunta não pode ser nulo.");
        }

        this.id = id;
        this.question = question;
        this.studentId = studentId;
        this.disciplineId = disciplineId;
        this.isPublic = isPublic;
        this.status = status;
        this.monitorId = monitorId;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public Long getMonitorId() {
        return monitorId;
    }
}
