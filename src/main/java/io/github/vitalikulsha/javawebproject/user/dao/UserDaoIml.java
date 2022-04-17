package io.github.vitalikulsha.javawebproject.user.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.CommonSqlQuery;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class UserDaoIml extends AbstractDao<User> implements UserDao {
    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String SQL_SELECT_BY_ROLE = "SELECT * FROM user WHERE role=? LIMIT ?, ?";
    private static final String SQL_INSERT = "INSERT INTO user" +
            " (login, password, firstname, lastname, phonenumber, email, role) VALUES ?, ?, ?, ?, ?, ?, ?";
    private static final String SQL_UPDATE = "UPDATE user SET login=?, password=?, firstname=?, lastname=?," +
            " phonenumber=?, email=?, role=? WHERE user_id=?";
    private static final String SQL_SELECT_COUNT_BY_ROLE = "SELECT COUNT(*) FROM user WHERE role=?";

    public UserDaoIml() {
        super(RowMapperFactory.instance().userRowMapper(),
                new CommonSqlQuery(Table.USER, Column.USER_ID));
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        return queryOperator.executeSingleEntityQuery(SQL_SELECT_BY_LOGIN, login);
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return queryOperator.executeSingleEntityQuery(SQL_SELECT_BY_EMAIL, email);
    }

    @Override
    public List<User> findByRole(int fromIndex, int itemsOnPage, Role role) throws DaoException {
        return queryOperator.executeEntityListQuery(SQL_SELECT_BY_ROLE, role.name(), fromIndex, itemsOnPage);
    }

    @Override
    public int save(User user) throws DaoException {
        return queryOperator.executeUpdate(SQL_INSERT, user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getRole().name());
    }

    @Override
    public int update(User user) throws DaoException {
        return queryOperator.executeUpdate(SQL_UPDATE, user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getRole().name(), user.getId());
    }

    @Override
    public int countByRoleParam(Role role) throws DaoException {
        return queryOperator.executeCountQuery(SQL_SELECT_COUNT_BY_ROLE, role.name());
    }
}
