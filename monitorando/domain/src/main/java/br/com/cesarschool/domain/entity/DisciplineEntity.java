package br.com.cesarschool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineEntity {
    private Long id;
    private String name;
    private String code;
    private List<StudentEntity> students;
    private List<MonitorEntity>  monitors;

    public DisciplineEntity(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
