package br.com.cesarschool.presentation.handler;

import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import br.com.cesarschool.domain.exception.LoginIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
        return getMapResponseEntity(ex.getMessage(), ex);
    }

    @ExceptionHandler(LoginIncorrectException.class)
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyInUse(LoginIncorrectException ex) {
        return getMapResponseEntity(ex.getMessage(), ex);
    }

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(String message, RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", message);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
