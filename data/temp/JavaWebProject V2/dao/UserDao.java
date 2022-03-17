package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.User;

public interface UserDao extends Dao<User>{
    User getByLogin(String login);

    boolean isExist(String login, String password);

    int maxId();
}
