package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author getEntity(ResultSet resultSet) throws SQLException {
        try {
            int id = resultSet.getInt(Column.AUTHOR_ID.name());
            String firstName = resultSet.getString(Column.FIRSTNAME.name());
            String lastName = resultSet.getString(Column.LASTNAME.name());
            return new Author(id, firstName, lastName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
