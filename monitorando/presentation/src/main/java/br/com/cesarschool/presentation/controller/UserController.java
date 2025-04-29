package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.port.user.FindUserPort;
import br.com.cesarschool.application.port.user.LoginUserPort;
import br.com.cesarschool.application.port.user.RegisterUserPort;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.service.UserService;
import br.com.cesarschool.infrastructure.adapter.user.FindUserAdapter;
import br.com.cesarschool.infrastructure.adapter.user.LoginUserAdapter;
import br.com.cesarschool.infrastructure.adapter.user.RegisterUserAdapter;
import br.com.cesarschool.infrastructure.repository.UserRepository;
import br.com.cesarschool.presentation.dto.user.UserLoginRequest;
import br.com.cesarschool.presentation.dto.user.UserLoginResponse;
import br.com.cesarschool.presentation.dto.user.UserRegisterRequest;
import br.com.cesarschool.presentation.dto.user.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        userService.register(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(), request.getRole());
        return new UserRegisterResponse("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        UserEntity user = userService.login(request.getEmail(), request.getPassword());

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
