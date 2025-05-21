package br.com.cesarschool.domain.repository.user;

public interface LoginUserRepository<T> {
    T loginUser(String email, String password);
}
