package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.service.UserService;
import br.com.cesarschool.presentation.dto.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/section")
    public ResponseEntity<UserSectionResponse> section(@RequestParam Long id) {
        Boolean section = userService.getUserAutenticado(id);

        UserSectionResponse response = new UserSectionResponse(
                section
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserLogoutResponse> logout(@RequestBody UserLogoutRequest request) {
        userService.logout(request.id());
        return ResponseEntity.ok(new UserLogoutResponse("Logout com sucesso!"));
    }

}
