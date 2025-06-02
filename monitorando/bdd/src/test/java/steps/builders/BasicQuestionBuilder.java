//package steps.builders;
//
//import br.com.cesarschool.domain.entity.enums.QuestionStatus;
//import br.com.cesarschool.domain.entity.DisciplineEntity;
//import br.com.cesarschool.domain.entity.MonitorEntity;
//import br.com.cesarschool.domain.entity.QuestionEntity;
//import br.com.cesarschool.domain.entity.StudentEntity;
//import br.com.cesarschool.domain.entity.enums.UserRole;
//import steps.interfaces.QuestionBuilder;
//
//import java.util.List;
//
//public class BasicQuestionBuilder implements QuestionBuilder {
//
//    private QuestionEntity questionEntity;
//
//    private Long id;
//    private String question;
//    private StudentEntity student;
//    private DisciplineEntity discipline;
//    private Boolean isPublic;
//    private QuestionStatus status;
//    private MonitorEntity monitor;
//
//    @Override
//    public void buildId() {
//        this.id = 1L;
//    }
//
//    @Override
//    public void buildQuestion() {
//        this.question = "O que é polimorfismo em Java?";
//    }
//
//    @Override
//    public void buildStudent() {
//        this.discipline = new DisciplineEntity(
//                1L,
//                "Matemática",
//                "MAT101",
//                null,
//                null
//        );
//
//        this.student = new StudentEntity(
//                100L,
//                "Lucas",
//                "Silva",
//                "lucas.silva@cesarschool.com",
//                "123456",
//                UserRole.STUDENT,
//                List.of(discipline)
//        );
//    }
//
//    @Override
//    public void buildDiscipline() {
//        this.discipline = new DisciplineEntity(
//                1L,
//                "Matemática",
//                "MAT101",
//                null,
//                null
//        );
//    }
//
//    @Override
//    public void buildIsPublic() {
//        this.isPublic = true;
//    }
//
//    @Override
//    public void buildStatus() {
//        this.status = QuestionStatus.PENDING;
//    }
//
//    @Override
//    public void buildMonitor() {
//        this.monitor = new MonitorEntity(
//                200L,
//                "Marina",
//                "Fernandes",
//                "marina.fernandes@cesarschool.com",
//                "abc123",
//                UserRole.MONITOR,
//                List.of(discipline),
//                List.of(),
//                discipline
//        );
//    }
//
//    public QuestionEntity getQuestionEntity() {
//        return new QuestionEntity(
//                id,
//                question,
//                student,
//                discipline,
//                isPublic,
//                status,
//                monitor
//        );
//    }
//
//    public QuestionEntity getQuestion() {
//        return this.questionEntity;
//    }
//}