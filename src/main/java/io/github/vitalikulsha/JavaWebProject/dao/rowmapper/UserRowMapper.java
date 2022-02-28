package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        try {
            int id = resultSet.getInt(Column.USER_ID.name());
            String login = resultSet.getString(Column.LOGIN.name());
            String password = resultSet.getString(Column.PASSWORD.name());
            String userName = resultSet.getString(Column.USERNAME.name());
            long phoneNumber = resultSet.getLong(Column.PHONENUMBER.name());
            String email = resultSet.getString(Column.EMAIL.name());
            Role role = Role.valueOf(resultSet.getString(Column.ROLE.name()));
            return new User(id, login, password, userName, phoneNumber, email, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
