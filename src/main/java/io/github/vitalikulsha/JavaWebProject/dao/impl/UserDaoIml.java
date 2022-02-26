package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.UserSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.User;

public class UserDaoIml extends AbstractDao<User> implements UserDao {
    private final static UserSqlQuery userSqlQuery = SqlQueryFactory.instance().userSqlQuery();

    public UserDaoIml() {
        super(RowMapperFactory.instance().userRowMapper(),
                userSqlQuery.FIND_ALL, userSqlQuery.FIND_BY_ID);
    }

    @Override
    public User getByLogin(String login) {
        return queryOperator.executeSingleEntityQuery(userSqlQuery.FIND_BY_LOGIN, login);
    }

    @Override
    public int maxId() {
        return queryOperator.executeMaxIdQuery(userSqlQuery.FIND_MAX_ID);
    }

    @Override
    public boolean isExist(String login, String password) {
        User user = getByLogin(login);
        if (user == null) {
            return false;
        } else return user.getLogin().equals(login)
                && user.getPassword().equals(password);
    }
}
