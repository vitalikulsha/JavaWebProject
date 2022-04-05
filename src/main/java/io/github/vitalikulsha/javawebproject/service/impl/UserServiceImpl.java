package io.github.vitalikulsha.javawebproject.service.impl;

import io.github.vitalikulsha.javawebproject.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.dao.UserDao;
import io.github.vitalikulsha.javawebproject.entity.converter.DtoConverter;
import io.github.vitalikulsha.javawebproject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.javawebproject.entity.dto.UserDto;
import io.github.vitalikulsha.javawebproject.entity.Role;
import io.github.vitalikulsha.javawebproject.entity.User;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.service.UserService;
import io.github.vitalikulsha.javawebproject.service.validator.EntityValidator;
import io.github.vitalikulsha.javawebproject.service.validator.ValidationPattern;
import io.github.vitalikulsha.javawebproject.service.validator.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    public UserDto getById(int id) throws ServiceException {
        try {
            return userDtoConverter.toDto(userDao.findById(id));
        } catch (DaoException e) {
            log.error("Unable to get user by id.");
            throw new ServiceException("Exception when getting user from DB by id.", e);
        }
    }

    @Override
    public List<UserDto> getAll() throws ServiceException {
        try {
            return userDao.findAll()
                    .stream()
                    .map(userDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get all users.");
            throw new ServiceException("Exception when getting all users from DB.", e);
        }
    }

    @Override
    public List<UserDto> getUsersByRole(Role role) throws ServiceException {
        try {
            return userDao.findByRole(role)
                    .stream()
                    .map(userDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting users from DB by role.", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        try {
            return userDao.deleteById(id) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when deleting a user", e);
        }
    }

    @Override
    public boolean isExists(String login, String password) throws ServiceException {
        try {
            User user = userDao.findByLogin(login);
            if (user == null) {
                return false;
            } else return user.getLogin().equals(login)
                    && user.getPassword().equals(DigestUtils.sha256Hex(password));
        } catch (DaoException e) {
            throw new ServiceException("Exception when comparing user by login and password.", e);
        }
    }

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        try {
            return userDtoConverter.toDto(userDao.findByLogin(login));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting user from DB by login.", e);
        }
    }

    @Override
    public UserDto getByEmail(String email) throws ServiceException {
        try {
            return userDtoConverter.toDto(userDao.findByEmail(email));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting user from DB by email.", e);
        }
    }

    @Override
    public boolean createUser(String login, String password, String firstName, String lastName,
                              long phoneNumber, String email) throws ServiceException {
        if (password == null || !password.matches(ValidationPattern.PASSWORD_PATTERN)
                || login == null || !login.matches(ValidationPattern.LOGIN_PATTERN)) {
            log.error("Password or login is invalid.");
            return false;
        }
        User user = new User(0, login, DigestUtils.sha256Hex(password),
                firstName, lastName, phoneNumber, email, Role.READER);
        if (!userValidator.validate(user)) {
            log.error("User is invalid.");
            return false;
        }
        try {
            return userDao.save(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when creating new user.", e);
        }
    }

    @Override
    public boolean editUser(String firstName, String lastName, long phoneNumber,
                            String email, int userId) throws ServiceException {
        try {
            User user = userDao.findById(userId);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            if (userValidator.validate(user)) {
                log.error("User is invalid.");
                return false;
            }
            return userDao.update(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when updating user.", e);
        }
    }
}
