package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.domain.repository.user.FindUserRepository;
import br.com.cesarschool.domain.repository.user.LoginUserRepository;
import br.com.cesarschool.domain.repository.user.PromoteUserToStudentRepository;
import br.com.cesarschool.domain.repository.user.RegisterUserRepository;
import br.com.cesarschool.domain.entity.UserEntity;
import br.com.cesarschool.domain.exception.EmailAlreadyInUseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final RegisterUserRepository registerUserRepository;
    private final PromoteUserToStudentRepository promoteUserToStudentRepository;
    private final FindUserRepository<UserEntity> findUserRepository;
    private final LoginUserRepository<UserEntity> loginUserRepository;
    private final FindDisciplineRepository<DisciplineEntity> findDisciplineRepository;

    public UserService(RegisterUserRepository registerUserRepository, PromoteUserToStudentRepository promoteUserToStudentRepository, FindUserRepository<UserEntity> findUserRepository, LoginUserRepository<UserEntity> loginUserRepository, FindDisciplineRepository<DisciplineEntity> findDisciplineRepository) {
        this.registerUserRepository = registerUserRepository;
        this.promoteUserToStudentRepository = promoteUserToStudentRepository;
        this.findUserRepository = findUserRepository;
        this.loginUserRepository = loginUserRepository;
        this.findDisciplineRepository = findDisciplineRepository;
    }

    public void register(String name, String surname, String email, String password, String role) {
        Optional<UserEntity> userOptional = findUserRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new EmailAlreadyInUseException("Já existe um usuário cadastrado com o e-mail: " + email);
        }

        registerUserRepository.register(name, surname, email, password, role);

        if ("STUDENT".equalsIgnoreCase(role)) {
            Optional<UserEntity> newUser = findUserRepository.findByEmail(email);
            newUser.ifPresent(user -> promoteUserToStudentRepository.promote(user.getId()));
        }
    }

    public UserEntity login(String email, String password) {
        UserEntity user = loginUserRepository.loginUser(email, password);

        user.setActive(true);

        return user;
    }

    public Boolean getUserAutenticado(Long id) {
        return loginUserRepository.hasLoginUser(id);
    }

    public void logout(Long id) {
        loginUserRepository.logoutUser(id);
    }

    public void promoteToMonitor(Long idUser, Long idDiscipline) {
        UserEntity user = findUserRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found id: " + idUser));

        if (!user.getRole().equals("STUDENT")) {
            throw new IllegalArgumentException("User is not a student");
        }

        DisciplineEntity discipline = findDisciplineRepository.findById(idDiscipline)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found id: " + idDiscipline));

        user.setRole(UserRole.MONITOR);


        promoteUserToStudentRepository.promoteMonitor(user.getId(), discipline.getId());
    }

}

