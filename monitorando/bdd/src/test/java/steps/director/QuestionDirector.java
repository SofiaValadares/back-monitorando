package steps.director;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import steps.interfaces.QuestionBuilder;

public class QuestionDirector {
    private final QuestionBuilder builder;

    public QuestionDirector(QuestionBuilder builder) {
        this.builder = builder;
    }

    public void constructBasicQuestion() {
        builder.buildId();
        builder.buildQuestion();
        builder.buildStudent();
        builder.buildDiscipline();
        builder.buildIsPublic();
        builder.buildStatus();
        builder.buildMonitor();
    }

    public void constructQuestionWithStudentOnly() {
        builder.buildId();
        builder.buildStudent();
    }

    public void constructQuestionWithStudentAndMonitor() {
        builder.buildId();
        builder.buildStudent();
        builder.buildMonitor();
        builder.buildQuestion();
        builder.buildDiscipline();
        builder.buildIsPublic();
        builder.buildStatus();
    }
}
