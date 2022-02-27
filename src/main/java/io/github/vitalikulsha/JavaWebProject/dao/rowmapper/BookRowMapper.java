package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book getEntity(ResultSet resultSet) throws SQLException {
        try {
            int id = resultSet.getInt(Column.BOOK_ID.name());
            String title = resultSet.getString(Column.TITLE.name());
            int publicationYear = resultSet.getInt(Column.PUBLICATIONYEAR.name());
            int numberPages = resultSet.getInt(Column.NUMBERPAGES.name());
            int categoryId = resultSet.getInt(Column.CATEGORY.name());
            int number = resultSet.getInt(Column.NUMBER.name());
            return new Book(id, title, publicationYear, numberPages, categoryId, number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
