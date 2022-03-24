package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;

import java.util.List;

public interface UserService extends Service<UserDto> {
    List<UserDto> getUsersByRole(Role role);

    boolean isExists(String login, String password);

    UserDto getByLogin(String login);

    UserDto getByEmail(String email);

    boolean createUser(String login, String password, String firstName, String lastName,
                       long phoneNumber, String email);

    boolean editUser(String firstName, String lastName, long phoneNumber, String email, int userId);
}
