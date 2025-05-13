package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.question.QuestionChatPort;
import br.com.cesarschool.application.port.question.QuestionToMonitorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionToMonitorPort questionToMonitorPort;
    private final QuestionChatPort questionChatPort;

    public void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId) {
        questionToMonitorPort.makeQuestionToMonitor(studentId, question, disciplineId, monitorId);
    }


}
