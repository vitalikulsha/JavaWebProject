package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

/**
 * User DAO interface
 * See also:
 * {@link Dao}
 * {@link AbstractDao}
 */
public interface UserDao extends Dao<User> {
    /**
     * Finds a user by login.
     *
     * @param login user login to search
     * @return found user
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    User findByLogin(String login) throws DaoException;

    /**
     * Finds a user by e-mail.
     *
     * @param email user e-mail to search
     * @return found user
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    User findByEmail(String email) throws DaoException;

    /**
     * Finds a list of users with the given role.
     *
     * @param role user role to search
     * @return found list of users
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<User> findByRole(Role role) throws DaoException;

    /**
     * Updates user data.
     *
     * @param user new user
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int update(User user) throws DaoException;
}
