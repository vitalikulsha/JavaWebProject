package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Category;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book getEntity(ResultSet resultSet) throws SQLException {
//        try {
//            int id = resultSet.getInt(Column.USER_ID.name());
//            String login = resultSet.getString(Column.LOGIN.name());
//            String password = resultSet.getString(Column.PASSWORD.name());
//            String userName = resultSet.getString(Column.USERNAME.name());
//            long phoneNumber = resultSet.getLong(Column.PHONENUMBER.name());
//            String email = resultSet.getString(Column.EMAIL.name());
//            User.Role role = User.Role.valueOf(resultSet.getString(Column.ROLE.name()));
//            return new Book(id, login, password, userName, phoneNumber, email, role);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }


//    private Book getBook(ResultSet resultSet) {
//        try {
//            int id = resultSet.getInt("book_id");
//            String title = resultSet.getString("title");
//            int yearIssue = resultSet.getInt("yearIssue");
//            int numberPages = resultSet.getInt("numberPages");
//            int categoryId = resultSet.getInt("category");
//            String categoryName = resultSet.getString("name");
//            List<Author> authors = getAuthors(id);
//            return new Book(id, title, authors, yearIssue, numberPages, new Category(categoryId, categoryName));
//        } catch (SQLException e) {
//            log.error(e.toString());
//            return null;
//        }
//    }
//
//    private List<Author> getAuthors(Integer bookId) {
//        List<Author> authors = new ArrayList<>();
//        String sqlQuery = "SELECT * FROM book_author b_a" +
//                " INNER JOIN author ON b_a.author_id=author.author_id" +
//                " WHERE b_a.book_id=?";
//        try (Connection connection = connectionSource.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
//            preparedStatement.setInt(1, bookId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int authorId = resultSet.getInt("author_id");
//                String firstName = resultSet.getString("firstName");
//                String lastName = resultSet.getString("lastName");
//                authors.add(new Author(authorId, firstName, lastName));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return authors;
//    }
}
