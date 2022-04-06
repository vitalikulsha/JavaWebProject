package io.github.vitalikulsha.javawebproject.user.service;

import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.service.validator.EntityValidator;
import io.github.vitalikulsha.javawebproject.util.service.validator.ValidationPattern;
import io.github.vitalikulsha.javawebproject.util.service.validator.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final EntityValidator<User> userValidator;

    public UserServiceImpl() {
        userDao = DaoFactory.instance().userDao();
        userDTOConverter = DTOConverterFactory.instance().userDtoConverter();
        userValidator = ValidatorFactory.instance().userValidator();
    }

    @Override
    public UserDTO getById(int id) throws ServiceException {
        try {
            return userDTOConverter.toDto(userDao.findById(id));
        } catch (DaoException e) {
            log.error("Unable to get user by id.");
            throw new ServiceException("Exception when getting user from DB by id.", e);
        }
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        try {
            return userDao.findAll()
                    .stream()
                    .map(userDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get all users.");
            throw new ServiceException("Exception when getting all users from DB.", e);
        }
    }

    @Override
    public List<UserDTO> getUsersByRole(Role role) throws ServiceException {
        try {
            return userDao.findByRole(role)
                    .stream()
                    .map(userDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting users from DB by role.", e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            userDao.deleteById(id);
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
    public UserDTO getByLogin(String login) throws ServiceException {
        try {
            return userDTOConverter.toDto(userDao.findByLogin(login));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting user from DB by login.", e);
        }
    }

    @Override
    public UserDTO getByEmail(String email) throws ServiceException {
        try {
            return userDTOConverter.toDto(userDao.findByEmail(email));
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
