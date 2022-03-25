package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.UserSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

public class UserDaoIml extends AbstractDao<User> implements UserDao {
    private final static UserSqlQuery userSqlQuery = SqlQueryFactory.instance().userSqlQuery();

    public UserDaoIml() {
        super(RowMapperFactory.instance().userRowMapper(),
                userSqlQuery.FIND_ALL, userSqlQuery.FIND_BY_ID, userSqlQuery.DELETE_BY_ID);
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        return queryOperator.executeSingleEntityQuery(userSqlQuery.FIND_BY_LOGIN, login);
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return queryOperator.executeSingleEntityQuery(userSqlQuery.FIND_BY_EMAIL, email);
    }

    @Override
    public List<User> findByRole(Role role) throws DaoException {
        return queryOperator.executeEntityListQueryWithParam(userSqlQuery.FIND_BY_ROLE, role.name());
    }

    @Override
    public boolean isExist(String login, String password) throws DaoException {
        User user = findByLogin(login);
        if (user == null) {
            return false;
        } else return user.getLogin().equals(login)
                && user.getPassword().equals(password);
    }

    @Override
    public int save(User user) throws DaoException {
        return queryOperator.executeUpdate(userSqlQuery.SAVE, user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getRole().name());
    }

    @Override
    public int update(User user) throws DaoException {
        return queryOperator.executeUpdate(userSqlQuery.UPDATE, user.getFirstName(), user.getLastName(),
                user.getPhoneNumber(), user.getEmail(), user.getId());
    }
}
