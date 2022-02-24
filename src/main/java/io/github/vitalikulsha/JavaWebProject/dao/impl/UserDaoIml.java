package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.UserSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoIml extends AbstractDao<User> implements UserDao {

    public UserDaoIml() {
        super(RowMapperFactory.getInstance().getUserRowMapper(),
                UserSqlQuery.FIND_ALL, UserSqlQuery.FIND_BY_ID);
    }

    @Override
    public User getByLogin(String login) {
        return queryOperator.executeSingleEntityQuery(UserSqlQuery.FIND_BY_LOGIN, login);
    }

//    @Override
//    public User getByLogin(String login) {
//        String sqlQuery = "SELECT * FROM user WHERE login=?";
//        try (Connection connection = connectionSource.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
//            preparedStatement.setString(1, login);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return getUser(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



    @Override
    public int maxId() {
        return queryOperator.executeMaxIdQuery(UserSqlQuery.FIND_MAX_ID);
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
