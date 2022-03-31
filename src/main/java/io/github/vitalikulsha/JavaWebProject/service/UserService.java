package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

/**
 * User service interface
 */
public interface UserService extends Service<UserDto> {
    /**
     * Gets a list of users DTO by role.
     *
     * @param role  user role
     * @return list of users DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<UserDto> getUsersByRole(Role role) throws ServiceException;

    boolean isExists(String login, String password) throws ServiceException;

    UserDto getByLogin(String login) throws ServiceException;

    UserDto getByEmail(String email) throws ServiceException;

    boolean createUser(String login, String password, String firstName, String lastName,
                       long phoneNumber, String email) throws ServiceException;

    boolean editUser(String firstName, String lastName, long phoneNumber, String email, int userId) throws ServiceException;
}
