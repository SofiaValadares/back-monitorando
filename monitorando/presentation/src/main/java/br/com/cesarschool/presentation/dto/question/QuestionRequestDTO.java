package br.com.cesarschool.presentation.dto.question;



public class QuestionRequestDTO {
    private Long studentId;
    private String question;
    private Long disciplineId;
    private Long monitorId;

    public QuestionRequestDTO() {
    }

    public QuestionRequestDTO(Long studentId, String question, Long disciplineId, Long monitorId) {
        this.studentId = studentId;
        this.question = question;
        this.disciplineId = disciplineId;
        this.monitorId = monitorId;
    }


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }
}
