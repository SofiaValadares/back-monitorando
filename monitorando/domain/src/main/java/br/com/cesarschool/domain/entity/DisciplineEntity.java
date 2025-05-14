package br.com.cesarschool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineEntity {
    private Long id;
    private String name;
    private String code;
    private List<StudentEntity> students;
    private List<MonitorEntity>  monitors;

    public Optional<UserEntity> findUserInDiscipline(Long userId) {
        if (students != null) {
            for (StudentEntity student : students) {
                if (student.getId().equals(userId)) {
                    return Optional.of(student);
                }
            }
        }

        if (monitors != null) {
            for (MonitorEntity monitor : monitors) {
                if (monitor.getId().equals(userId)) {
                    return Optional.of(monitor);
                }
            }
        }

        return Optional.empty();
    }

}
