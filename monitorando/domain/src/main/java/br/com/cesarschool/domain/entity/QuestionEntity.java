package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import lombok.Getter;


public class QuestionEntity {
    private Long id;
    private String question;
    private StudentEntity student;
    private DisciplineEntity discipline;
    private Boolean isPublic;
    private QuestionStatus status;
    private MonitorEntity monitor;

    public QuestionEntity(
            Long id,
            String question,
            StudentEntity student,
            DisciplineEntity discipline,
            Boolean isPublic,
            QuestionStatus status,
            MonitorEntity monitor
    ) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("A pergunta não pode estar vazia.");
        }
        if (student == null) {
            throw new IllegalArgumentException("Pergunta deve estar associada a um aluno.");
        }
        if (discipline == null) {
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
        this.student = student;
        this.discipline = discipline;
        this.isPublic = isPublic;
        this.status = status;
        this.monitor = monitor;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public MonitorEntity getMonitor() {
        return monitor;
    }
}
