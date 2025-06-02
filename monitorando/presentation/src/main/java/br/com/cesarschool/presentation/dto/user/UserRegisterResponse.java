package br.com.cesarschool.presentation.dto.user;


public class UserRegisterResponse {
    private String message;

    public UserRegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
