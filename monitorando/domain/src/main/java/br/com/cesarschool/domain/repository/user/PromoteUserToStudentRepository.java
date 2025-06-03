package br.com.cesarschool.domain.repository.user;


public interface PromoteUserToStudentRepository {
    void promote(Long userId);

    void promoteMonitor(Long userId, Long disciplineId);
}
