package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final DtoConverter<UserDto, User> userDtoConverter;

    public UserServiceImpl() {
        userDao = DaoFactory.instance().userDao();
        userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
    }

    @Override
    public UserDto getById(int id) {
        return userDtoConverter.toDto(userDao.findById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(userDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByRole(Role role) {
        return userDao.findByRole(role)
                .stream()
                .map(userDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User save(UserDto user) {
        return null;
    }

    @Override
    public void delete(UserDto user) {

    }

    @Override
    public boolean isExists(String login, String password) {
        User user = userDao.findByLogin(login);
        if (user == null) {
            return false;
        } else return user.getLogin().equals(login)
                && user.getPassword().equals(password);
    }

    @Override
    public UserDto getByLogin(String login){
        return userDtoConverter.toDto(userDao.findByLogin(login));
    }
}
