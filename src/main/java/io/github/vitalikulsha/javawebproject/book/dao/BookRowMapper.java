package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book getEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Column.BOOK_ID.name());
        String title = resultSet.getString(Column.TITLE.name());
        int publicationYear = resultSet.getInt(Column.PUBLICATIONYEAR.name());
        int numberPages = resultSet.getInt(Column.NUMBERPAGES.name());
        int categoryId = resultSet.getInt(Column.CATEGORY.name());
        int quantity = resultSet.getInt(Column.QUANTITY.name());
        return new Book(id, title, publicationYear, numberPages, categoryId, quantity);
    }
}
