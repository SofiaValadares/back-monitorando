package br.com.cesarschool.presentation.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;

    public UserRegisterRequest() {}

}
