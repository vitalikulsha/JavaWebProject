package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Book;
import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;
import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                BookCatalog bookCatalog = getBookCatalog(resultSet);
                if (bookCatalog != null) {
                    return Optional.ofNullable(getBookCatalog(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BookCatalog> getAll() {
        String sqlQuery = "SELECT * FROM book_catalog";
        return getBookCatalogList(null, sqlQuery);
    }

    @Override
    public List<BookCatalog> getByName(String title) {
        String sqlQuery = "SELECT * FROM book_catalog b_c" +
                " INNER JOIN book ON b_c.book_id=book.book_id" +
                " WHERE title LIKE '%%%s%%'";
        return getBookCatalogList(title, sqlQuery);
    }

    @Override
    public List<BookCatalog> getByAuthorName(String name) {
        String sqlQuery = "SELECT * FROM book_catalog b_c" +
                " INNER JOIN book_author b_a ON b_c.book_id=b_a.book_id" +
                " INNER JOIN author ON b_a.author_id=author.author_id" +
                " WHERE firstName LIKE '%%%s%%' OR lastName LIKE '%%%s%%'";
        return getBookCatalogList(name, sqlQuery);
    }

    @Override
    public List<BookCatalog> getByCategoryName(String name) {
        String sqlQuery = "SELECT * FROM book_catalog b_c" +
                " INNER JOIN book ON b_c.book_id=book.book_id" +
                " INNER JOIN category cat ON book.category=cat.category_id" +
                " WHERE name LIKE '%%%s%%'";
        return getBookCatalogList(name, sqlQuery);
    }

    @Override
    public BookCatalog save(BookCatalog bookCatalog) {
        return null;
    }

    @Override
    public void delete(BookCatalog bookCatalog) {

    }

    private List<BookCatalog> getBookCatalogList(String name, String sqlQuery) {
        List<String> names = new ArrayList<>();
        Pattern pattern = Pattern.compile("%s%");
        Matcher matcher = pattern.matcher(sqlQuery);
        while (matcher.find()) {
            names.add(name);
        }
        List<BookCatalog> catalogs = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlQuery, names.toArray()));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                BookCatalog bookCatalog = getBookCatalog(resultSet);
                if (bookCatalog != null) {
                    catalogs.add(getBookCatalog(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs.isEmpty() ? null : catalogs;
    }

    private BookCatalog getBookCatalog(ResultSet resultSet) {
        BookDao bookDao = factory.bookDao();
        try {
            Integer bookId = resultSet.getInt("book_id");
            Integer number = resultSet.getInt("number");
            Book book = bookDao.getById(bookId).get();
            return number.equals(0) ? null : new BookCatalog(book, number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
