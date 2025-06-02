//package steps.builders;
//
//import br.com.cesarschool.domain.entity.DisciplineEntity;
//import br.com.cesarschool.domain.entity.StudentEntity;
//import br.com.cesarschool.domain.entity.enums.UserRole;
//import steps.interfaces.StudentBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BasicStudentBuilder implements StudentBuilder {
//    private Long id;
//    private String name;
//    private String surname;
//    private String email;
//    private String password;
//    private UserRole role;
//    private List<DisciplineEntity> disciplines;
//
//    @Override
//    public void buildId() {
//        this.id = 1L;
//    }
//
//    @Override
//    public void buildName() {
//        this.name = "Ana";
//    }
//
//    @Override
//    public void buildSurname() {
//        this.surname = "Silva";
//    }
//
//    @Override
//    public void buildEmail() {
//        this.email = "ana.silva@example.com";
//    }
//
//    @Override
//    public void buildPassword() {
//        this.password = "senha123";
//    }
//
//    @Override
//    public void buildRole() {
//        this.role = UserRole.STUDENT;
//    }
//
//    @Override
//    public void buildDisciplines() {
//        DisciplineEntity discipline1 = new DisciplineEntity(
//                101L,
//                "Matemática",
//                "MAT101",
//                new ArrayList<>(),
//                new ArrayList<>()
//        );
//
//        DisciplineEntity discipline2 = new DisciplineEntity(
//                102L,
//                "História",
//                "HIS102",
//                new ArrayList<>(),
//                new ArrayList<>()
//        );
//
//        this.disciplines = List.of(discipline1, discipline2);
//    }
//
//    @Override
//    public StudentEntity getStudentEntity() {
//        return new StudentEntity(id, name, surname, email, password, role, disciplines);
//    }
//
//}
