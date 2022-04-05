package io.github.vitalikulsha.javawebproject.user.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Column.USER_ID.name());
        String login = resultSet.getString(Column.LOGIN.name());
        String password = resultSet.getString(Column.PASSWORD.name());
        String firstName = resultSet.getString(Column.FIRSTNAME.name());
        String lastName = resultSet.getString(Column.LASTNAME.name());
        long phoneNumber = resultSet.getLong(Column.PHONENUMBER.name());
        String email = resultSet.getString(Column.EMAIL.name());
        Role role = Role.valueOf(resultSet.getString(Column.ROLE.name()));
        return new User(id, login, password, firstName, lastName, phoneNumber, email, role);
    }
}
