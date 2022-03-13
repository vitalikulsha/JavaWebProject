package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User findByLogin(String login);

    List<User> findByRole(Role role);

    boolean isExist(String login, String password);
}
