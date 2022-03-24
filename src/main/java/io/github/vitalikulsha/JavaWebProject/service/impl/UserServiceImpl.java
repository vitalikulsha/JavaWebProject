package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.service.validator.EntityValidator;
import io.github.vitalikulsha.JavaWebProject.service.validator.ValidationPattern;
import io.github.vitalikulsha.JavaWebProject.service.validator.ValidatorFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final DtoConverter<UserDto, User> userDtoConverter;
    private final EntityValidator<User> userValidator;

    public UserServiceImpl() {
        userDao = DaoFactory.instance().userDao();
        userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
        userValidator = ValidatorFactory.instance().userValidator();
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
    public boolean createUser(String login, String password, String firstName, String lastName,
                              long phoneNumber, String email) {
        if (password == null || !password.matches(ValidationPattern.PASSWORD_PATTERN)
                || login == null || !login.matches(ValidationPattern.LOGIN_PATTERN)) {
            return false;
        }
        User user = new User(0, login, DigestUtils.sha256Hex(password),
                firstName, lastName, phoneNumber, email, Role.READER);
        return userValidator.validate(user) && userDao.save(user) == 1;
    }

    @Override
    public boolean editUser(String firstName, String lastName, long phoneNumber, String email, int userId) {
        User user = userDao.findById(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return userValidator.validate(user) && userDao.update(user) == 1;
    }
}
