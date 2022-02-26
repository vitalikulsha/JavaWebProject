package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.RecordBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordBookRowMapper implements RowMapper<RecordBook> {
    private final static DaoFactory factory = DaoFactory.instance();

    @Override
    public RecordBook getEntity(ResultSet resultSet) throws SQLException {
        BookDao bookDao = factory.bookDao();
        try {
            int id = resultSet.getInt(Column.BOOK_ID.name());
            int number = resultSet.getInt(Column.NUMBER.name());
            Book book = bookDao.getById(id);
            return new RecordBook(book, number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
