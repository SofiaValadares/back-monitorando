package br.com.cesarschool.presentation.dto.user;

public class UserSectionResponse {
    Boolean section;

    public UserSectionResponse() {
    }

    public UserSectionResponse(Boolean section) {
        this.section = section;
    }

    public Boolean getSection() {
        return section;
    }

    public void setSection(Boolean section) {
        this.section = section;
    }
}
