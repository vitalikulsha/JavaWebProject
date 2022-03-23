package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.entity.converter.UserDtoConverter;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

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
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean isExists(String login, String password) {
        User user = userDao.findByLogin(login);
        if (user == null) {
            return false;
        } else return user.getLogin().equals(login)
                && user.getPassword().equals(DigestUtils.sha256Hex(password));//encoder
    }

    @Override
    public UserDto getByLogin(String login) {
        return userDtoConverter.toDto(userDao.findByLogin(login));
    }

    @Override
    public UserDto getByEmail(String email) {
        return userDtoConverter.toDto(userDao.findByEmail(email));
    }

    @Override
    public boolean createUser(String login, String password, String userName, long phoneNumber,
                              String email) {
        User user = new User(0, login, DigestUtils.sha256Hex(password), userName, phoneNumber, email, Role.USER);
        return userDao.save(user) != 0;
    }

    @Override
    public UserDto updateUser(String userName, long phoneNumber, String email, int userId) {
        UserDtoConverter userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
        User user = userDao.findById(userId);
        User newUser = new User(userId, user.getLogin(), user.getPassword(),
                userName, phoneNumber, email, user.getRole());
        return userDao.update(newUser) != 0 ? userDtoConverter.toDto(newUser) : null;
    }
}
