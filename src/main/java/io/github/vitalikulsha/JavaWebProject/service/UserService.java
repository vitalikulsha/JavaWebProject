package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.util.List;

public interface UserService extends Service<UserDto>{
    List<UserDto> getUsersByRole(Role role);

    User save(UserDto user);

    boolean isExists(String login, String password);

    UserDto getByLogin(String login);
}
