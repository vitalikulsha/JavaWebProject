package io.github.vitalikulsha.javawebproject.author.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author getEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Column.AUTHOR_ID.name());
        String firstName = resultSet.getString(Column.FIRSTNAME.name());
        String lastName = resultSet.getString(Column.LASTNAME.name());
        return new Author(id, firstName, lastName);
    }
}
