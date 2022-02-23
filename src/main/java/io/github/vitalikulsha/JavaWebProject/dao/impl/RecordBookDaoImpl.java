package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.RecordBookDao;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.RecordBook;
import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordBookDaoImpl implements RecordBookDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final DaoFactory factory = new DaoFactory();

    @Override
    public RecordBook getById(Integer bookId) {
        String sqlQuery = "SELECT * FROM record_book WHERE book_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                RecordBook recordBook = getBookCatalog(resultSet);
                if (recordBook != null) {
                    return getBookCatalog(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RecordBook> getAll() {
        String sqlQuery = "SELECT * FROM record_book";
        return getBookCatalogList(null, sqlQuery);
    }

    @Override
    public List<RecordBook> getByName(String title) {
        String sqlQuery = "SELECT * FROM record_book r_b" +
                " INNER JOIN book ON r_b.book_id=book.book_id" +
                " WHERE title LIKE '%%%s%%'";
        return getBookCatalogList(title, sqlQuery);
    }

    @Override
    public List<RecordBook> getByAuthorName(String name) {
        String sqlQuery = "SELECT * FROM record_book r_b" +
                " INNER JOIN book_author b_a ON r_b.book_id=b_a.book_id" +
                " INNER JOIN author ON b_a.author_id=author.author_id" +
                " WHERE firstName LIKE '%%%s%%' OR lastName LIKE '%%%s%%'";
        return getBookCatalogList(name, sqlQuery);
    }

    @Override
    public List<RecordBook> getByCategoryName(String name) {
        String sqlQuery = "SELECT * FROM record_book r_b" +
                " INNER JOIN book ON r_b.book_id=book.book_id" +
                " INNER JOIN category cat ON book.category=cat.category_id" +
                " WHERE name LIKE '%%%s%%'";
        return getBookCatalogList(name, sqlQuery);
    }

    @Override
    public RecordBook save(RecordBook recordBook) {
        return null;
    }

    @Override
    public void delete(RecordBook recordBook) {

    }

    private List<RecordBook> getBookCatalogList(String name, String sqlQuery) {
        List<String> names = new ArrayList<>();
        Pattern pattern = Pattern.compile("%s%");
        Matcher matcher = pattern.matcher(sqlQuery);
        while (matcher.find()) {
            names.add(name);
        }
        List<RecordBook> catalogs = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlQuery, names.toArray()));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RecordBook recordBook = getBookCatalog(resultSet);
                if (recordBook != null) {
                    catalogs.add(getBookCatalog(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs.isEmpty() ? null : catalogs;
    }

    private RecordBook getBookCatalog(ResultSet resultSet) {
        BookDao bookDao = factory.bookDao();
        try {
            int bookId = resultSet.getInt("book_id");
            int number = resultSet.getInt("number");
            Book book = bookDao.getById(bookId);
            return number ==0 ? null : new RecordBook(book, number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
