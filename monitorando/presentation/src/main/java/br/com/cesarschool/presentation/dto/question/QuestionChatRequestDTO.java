package br.com.cesarschool.presentation.dto.question;



public class QuestionChatRequestDTO {
    private Long questionId;
    private Long userId;
    private String answer;

    public QuestionChatRequestDTO() {
    }

    public QuestionChatRequestDTO(Long questionId, Long userId, String answer) {
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
