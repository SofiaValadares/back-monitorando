package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.presentation.dto.user.UserLoginRequest;
import br.com.cesarschool.presentation.dto.user.UserLoginResponse;
import br.com.cesarschool.presentation.dto.user.UserRegisterRequest;
import br.com.cesarschool.presentation.dto.user.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserPort registerUserPort;
    private final LoginUserPort<UserEntity> loginUserPort;

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        registerUserPort.register(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(), request.getRole());
        return new UserRegisterResponse("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        UserEntity user = loginUserPort.loginUser(request.getEmail(), request.getPassword());

        UserLoginResponse response = new UserLoginResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }

}
