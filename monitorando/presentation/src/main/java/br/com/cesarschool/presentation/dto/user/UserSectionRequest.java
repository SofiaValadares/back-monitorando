package br.com.cesarschool.presentation.dto.user;

public class UserSectionRequest {
    Long id;

    public UserSectionRequest() {}

    public UserSectionRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
