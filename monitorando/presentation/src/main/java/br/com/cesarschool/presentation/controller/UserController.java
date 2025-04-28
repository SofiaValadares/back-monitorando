package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.presentation.dto.user.UserRegisterRequest;
import br.com.cesarschool.presentation.dto.user.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserPort registerUserPort;

    @PostMapping
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        registerUserPort.register(request.getName(), request.getEmail(), request.getPassword());
        return new UserRegisterResponse("Usuário registrado com sucesso!");
    }
}
