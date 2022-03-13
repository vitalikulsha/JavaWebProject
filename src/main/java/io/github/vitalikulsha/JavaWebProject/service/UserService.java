package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.util.List;

public interface UserService extends Service<User>{
    List<User> getUsersByRole(Role role);
}
