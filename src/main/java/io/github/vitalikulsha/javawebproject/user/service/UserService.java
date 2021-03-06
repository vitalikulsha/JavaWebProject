package io.github.vitalikulsha.javawebproject.user.service;

import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.service.Service;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;

import java.util.List;

/**
 * User DTO service interface
 * See also:
 * {@link Service}
 */
public interface UserService extends Service<UserDTO> {
    /**
     * Gets a list of users DTO by role with pagination.
     *
     * @param role        user role
     * @param pagination  an object of the Pagination class with pagination parameters
     * @return list of users DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<UserDTO> getUsersByRole(Role role, Pagination pagination) throws ServiceException;

    /**
     * Counts the number of users of a given role.
     *
     * @param role user role
     * @return number of users found
     * @throws ServiceException thrown when DAO exception occurs
     */
    int countByRole(Role role) throws ServiceException;

    /**
     * Checks if a user exists with the given password and login.
     *
     * @param login    user login
     * @param password user password
     * @return true if the user exists
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean isExists(String login, String password) throws ServiceException;

    /**
     * Gets user DTO by user login
     *
     * @param login user login
     * @return user DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    UserDTO getByLogin(String login) throws ServiceException;

    /**
     * Gets user DTO by user e-mail.
     *
     * @param email user e-mail
     * @return user DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    UserDTO getByEmail(String email) throws ServiceException;

    /**
     * Registers a new user.
     *
     * @param login       user login
     * @param password    user password
     * @param firstName   user first name
     * @param lastName    user last name
     * @param phoneNumber user phone number
     * @param email       user e-mail
     * @return true if the user was successfully registered
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean createUser(String login, String password, String firstName, String lastName,
                       long phoneNumber, String email) throws ServiceException;

    /**
     * Edits the personal data of the user with the given id.
     *
     * @param firstName   user first name
     * @param lastName    user last name
     * @param phoneNumber user phone number
     * @param email       user e-mail
     * @param userId      user id
     * @return true if the user's personal data has been successfully edited
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean editUser(String firstName, String lastName, long phoneNumber, String email, int userId)
            throws ServiceException;
}
