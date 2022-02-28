package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.instance().userDao();
    }

    @Override
    public User getById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
