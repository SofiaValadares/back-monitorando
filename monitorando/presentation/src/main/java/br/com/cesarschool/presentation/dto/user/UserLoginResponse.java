package br.com.cesarschool.presentation.dto.user;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;
}
