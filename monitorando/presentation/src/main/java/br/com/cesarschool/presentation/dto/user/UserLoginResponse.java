package br.com.cesarschool.presentation.dto.user;

import br.com.cesarschool.domain.entity.enums.UserRole;


public class UserLoginResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;

    public UserLoginResponse() {
    }

    public UserLoginResponse(Long id, String name, String surname, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
