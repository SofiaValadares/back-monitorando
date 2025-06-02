package br.com.cesarschool.infrastructure.persistence.entity;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class QuestionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentJpaEntity student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineJpaEntity discipline;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private MonitorJpaEntity monitor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionStatus status = QuestionStatus.PENDING;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionChatJpaEntity> chats;

    public QuestionJpaEntity() {
    }

    public QuestionJpaEntity(Long id, String question, StudentJpaEntity student, DisciplineJpaEntity discipline, Boolean isPublic, MonitorJpaEntity monitor, QuestionStatus status, List<QuestionChatJpaEntity> chats) {
        this.id = id;
        this.question = question;
        this.student = student;
        this.discipline = discipline;
        this.isPublic = isPublic;
        this.monitor = monitor;
        this.status = status;
        this.chats = chats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public StudentJpaEntity getStudent() {
        return student;
    }

    public void setStudent(StudentJpaEntity student) {
        this.student = student;
    }

    public DisciplineJpaEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineJpaEntity discipline) {
        this.discipline = discipline;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public MonitorJpaEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorJpaEntity monitor) {
        this.monitor = monitor;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }

    public List<QuestionChatJpaEntity> getChats() {
        return chats;
    }

    public void setChats(List<QuestionChatJpaEntity> chats) {
        this.chats = chats;
    }
}
