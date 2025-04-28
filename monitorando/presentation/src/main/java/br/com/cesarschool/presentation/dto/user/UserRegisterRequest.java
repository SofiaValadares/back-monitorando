package br.com.cesarschool.presentation.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;

    public UserRegisterRequest() {}

}
