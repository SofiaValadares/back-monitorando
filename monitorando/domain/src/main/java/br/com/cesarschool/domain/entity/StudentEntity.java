package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;

import java.util.List;

public class StudentEntity extends UserEntity {
    private List<DisciplineEntity> disciplines;

    public StudentEntity(Long id, String name, String surname, String email, String password, UserRole role, List<DisciplineEntity> disciplines) {
        super(id, name, surname, email, password, role);
        this.disciplines = disciplines;
    }

    public StudentEntity(UserEntity user, List<DisciplineEntity> disciplines) {
        super(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRole());
        this.disciplines = disciplines;
    }

    public List<DisciplineEntity> getDisciplines() {
        return disciplines;
    }

}
