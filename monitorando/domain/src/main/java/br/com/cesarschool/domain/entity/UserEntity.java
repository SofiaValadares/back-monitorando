package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole role;
}
