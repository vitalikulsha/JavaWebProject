package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Book;
import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;
import io.github.vitalikulsha.JavaWebProject.util.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookCatalogDaoImpl implements BookCatalogDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final DaoFactory factory = new DaoFactory();

    @Override
    public Optional<BookCatalog> getById(Integer bookId) {
        String sqlQuery = "SELECT * FROM book_catalog b_k WHERE book_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getBookCatalog(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BookCatalog> getAll() {
        List<BookCatalog> catalogs = new ArrayList<>();
        String sqlQuery = "SELECT * FROM book_catalog";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                catalogs.add(getBookCatalog(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return catalogs;
    }

    @Override
    public List<BookCatalog> getByName(String title) {
        List<BookCatalog> catalogs = new ArrayList<>();
        String sqlQuery = "SELECT * FROM book_catalog b_c" +
                " INNER JOIN book ON b_c.book_id=book.book_id" +
                " WHERE title LIKE '%%%s%%'";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlQuery, title));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                catalogs.add(getBookCatalog(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs;
    }

    @Override
    public BookCatalog save(BookCatalog bookCatalog) {
        return null;
    }

    @Override
    public void delete(BookCatalog bookCatalog) {

    }

    private BookCatalog getBookCatalog(ResultSet resultSet) {
        BookDao bookDao = factory.bookDao();
        try {
            Integer bookId = resultSet.getInt("book_id");
            Integer number = resultSet.getInt("number");
            Book book = bookDao.getById(bookId).get();
            return new BookCatalog(book, number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
