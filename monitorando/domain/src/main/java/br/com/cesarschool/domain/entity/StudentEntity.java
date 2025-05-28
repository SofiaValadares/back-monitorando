package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;

import java.util.List;

public class StudentEntity extends UserEntity {
    private List<Long> disciplineIds;

    public StudentEntity(Long id, String name, String surname, String email, String password, UserRole role, List<Long> disciplineIds) {
        super(id, name, surname, email, password, role);
        this.disciplineIds = disciplineIds;
    }

    public StudentEntity(UserEntity user, List<Long> disciplineIds) {
        super(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRole());
        this.disciplineIds = disciplineIds;
    }

    public List<Long> getDisciplineIds() {
        return disciplineIds;
    }

}
