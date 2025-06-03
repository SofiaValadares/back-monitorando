package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.global.RandomStringGenerator;

import java.util.List;

public class DisciplineEntity {
    private final Long id;
    private final String name;
    private final String code;
    private List<Long> studentsIds = List.of();
    private List<Long> monitorsIds = List.of();

    public DisciplineEntity(Long id, String name, String code, List<Long> students, List<Long> monitors) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome da disciplina não pode estar vazio.");
        }
        if (code == null || code.isBlank()) {
            code = RandomStringGenerator.generateUniqueString(8);
        }

        this.id = id;
        this.name = name;
        this.code = code;

        if (students != null && !students.isEmpty()) {
            this.studentsIds = students;
        }

        if (monitors != null && !monitors.isEmpty()) {
            this.monitorsIds = monitors;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Long> getStudentsIds() { return studentsIds; }

    public void addStudentId(Long studentId){ studentsIds.add(studentId); }

    public void removeStudentId(Long studentId) { studentsIds.remove(studentId); }

    public List<Long> getMonitorsIds() { return  monitorsIds; }

    public void addMonitorId(Long monitorId) { monitorsIds.add(monitorId); }

    public void removeMonitorId(Long monitorId) { monitorsIds.remove(monitorId); }
}
