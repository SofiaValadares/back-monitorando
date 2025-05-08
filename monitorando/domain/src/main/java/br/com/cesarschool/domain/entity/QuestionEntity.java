package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    private final Long id;
    private final String question;
    private final StudentEntity student;
    private final DisciplineEntity discipline;
    private final Boolean isPublic;
    private final QuestionStatus status;
    private MonitorEntity monitor;
}
