package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User>{
    User findByLogin(String login) throws DaoException;

    User findByEmail(String email) throws DaoException;

    List<User> findByRole(Role role) throws DaoException;

    boolean isExist(String login, String password) throws DaoException;

    int update(User user) throws DaoException;
}
