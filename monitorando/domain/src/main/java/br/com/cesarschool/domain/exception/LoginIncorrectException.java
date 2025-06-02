package br.com.cesarschool.domain.exception;

public class LoginIncorrectException extends RuntimeException {
    public LoginIncorrectException() {
        super("E-mail ou senha incorretos.");
    }
}
