package steps.director;

import br.com.cesarschool.domain.entity.StudentEntity;
import steps.builders.BasicStudentBuilder;

public class StudentDirector {
    private final BasicStudentBuilder builder;

    public StudentDirector(BasicStudentBuilder builder) {
        this.builder = builder;
    }

    public void constructStudent() {
        builder.buildId();
        builder.buildName();
        builder.buildSurname();
        builder.buildEmail();
        builder.buildPassword();
        builder.buildRole();
        builder.buildDisciplines();
    }

    public StudentEntity getStudent() {
        return builder.getStudentEntity();
    }
}
