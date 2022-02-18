package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Author;
import io.github.vitalikulsha.JavaWebProject.domain.Book;
import io.github.vitalikulsha.JavaWebProject.domain.Category;
import io.github.vitalikulsha.JavaWebProject.util.ConnectionSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BookDaoImpl implements BookDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Optional<Book> getById(Integer id) {
        String sqlQuery = "SELECT * FROM book INNER JOIN category cat ON book.category=cat.id WHERE book_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getByTitle(String title){
        List<Book> books = new ArrayList<>();
        String sqlQuery = "SELECT * FROM book INNER JOIN category cat ON book.category=cat.id WHERE title LIKE '%%%s%%'";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlQuery, title));
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
               books.add(getBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sqlQuery = "SELECT * FROM book LEFT JOIN category cat ON book.category=cat.id";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }

    private Book getBook(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            Integer yearIssue = resultSet.getInt("yearIssue");
            Integer numberPages = resultSet.getInt("numberPages");
            Integer categoryId = resultSet.getInt("category");
            String categoryName = resultSet.getString("name");
            List<Author> authors = getAuthors(id);
            return new Book(id, title, authors, yearIssue, numberPages, new Category(categoryId, categoryName));
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }

    private List<Author> getAuthors(Integer bookId) {
        String sqlQuery = "SELECT * FROM book_author b_a" +
                " LEFT JOIN author ON b_a.author_id=author.author_id" +
                " WHERE b_a.book_id=?";
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer authorId = resultSet.getInt("author_id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                authors.add(new Author(authorId, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return authors;
    }
}
