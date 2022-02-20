package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Integer>{
    Optional<User> getByLogin(String login);
}
