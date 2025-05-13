package br.com.cesarschool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChatEntity {
    private Long id;
    private QuestionEntity question;
    private UserEntity user;
    private String answer;
}
