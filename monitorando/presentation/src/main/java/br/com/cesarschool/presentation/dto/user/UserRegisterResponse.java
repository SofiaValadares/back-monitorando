package br.com.cesarschool.presentation.dto.user;

import lombok.Getter;

@Getter
public class UserRegisterResponse {
    private String message;

    public UserRegisterResponse(String message) {
        this.message = message;
    }

}
