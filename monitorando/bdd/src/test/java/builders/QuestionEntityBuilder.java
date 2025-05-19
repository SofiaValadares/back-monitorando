package builders;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.domain.entity.StudentEntity;

public class QuestionEntityBuilder {
    private Long id;
    private String question;
    private StudentEntity student;
    private DisciplineEntity discipline;
    private Boolean isPublic;
    private QuestionStatus status;
    private MonitorEntity monitor;

    public QuestionEntityBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public QuestionEntityBuilder comQuestion(String question) {
        this.question = question;
        return this;
    }

    public QuestionEntityBuilder comStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    public QuestionEntityBuilder comDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
        return this;
    }

    public QuestionEntityBuilder comIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public QuestionEntityBuilder comStatus(QuestionStatus status) {
        this.status = status;
        return this;
    }

    public QuestionEntityBuilder comMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
        return this;
    }

    public QuestionEntity build() {
        return new QuestionEntity(id, question, student, discipline, isPublic, status, monitor);
    }
}