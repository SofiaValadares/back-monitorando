package br.com.cesarschool.presentation.dto.question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionChatRequestDTO {
    private Long questionId;
    private Long userId;
    private String answer;
}
