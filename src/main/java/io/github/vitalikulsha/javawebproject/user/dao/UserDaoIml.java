package io.github.vitalikulsha.javawebproject.user.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class UserDaoIml extends AbstractDao<User> implements UserDao {
    private static final UserSqlQuery userSqlQuery = SqlQueryFactory.instance().userSqlQuery();

    public UserDaoIml() {
        super(RowMapperFactory.instance().userRowMapper(),
                userSqlQuery.FIND_ALL, userSqlQuery.FIND_BY_ID,
                userSqlQuery.DELETE_BY_ID, userSqlQuery.COUNT_ALL);
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
        return queryOperator.executeEntityListQuery(userSqlQuery.FIND_BY_ROLE, role.name());
    }

    @Override
    public int save(User user) throws DaoException {
        return queryOperator.executeUpdate(userSqlQuery.SAVE, user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getRole().name());
    }

    @Override
    public int update(User user) throws DaoException {
        return queryOperator.executeUpdate(userSqlQuery.UPDATE, user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getRole().name(), user.getId());
    }
}
