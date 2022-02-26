package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.BookSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final static BookSqlQuery bookSqlQuery = SqlQueryFactory.instance().bookSqlQuery();

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(), bookSqlQuery.FIND_ALL, bookSqlQuery.FIND_BY_ID);
    }

    @Override
    public List<Book> getByName(String title) {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_TITLE, title);
    }

    @Override
    public List<Book> getByCategoryName(String categoryName){
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_CATEGORY_NAME, categoryName);
    }

    @Override
    public List<Book> getByAuthorName(String authorName){
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_AUTHOR_NAME, authorName);
    }

    //    @Override
//    public List<Book> getByCategoryId(Integer categoryId) {
//        List<Book> books = new ArrayList<>();
//        String sqlQuery = "SELECT * FROM category cat INNER JOIN book ON cat.category_id=book.category WHERE id=?";
//        try (Connection connection = connectionSource.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
//            preparedStatement.setInt(1, categoryId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int bookId = resultSet.getInt("book_id");
//                books.add(getById(bookId));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }

//    @Override
//    public List<Book> getByAuthorId(Integer authorId) {
//        List<Book> books = new ArrayList<>();
//        String sqlQuery = "SELECT * FROM book_author b_a" +
//                " INNER JOIN book ON b_a.book_id=book.book_id" +
//                " WHERE b_a.author_id=?";
//        try (Connection connection = connectionSource.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
//            preparedStatement.setInt(1, authorId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int bookId = resultSet.getInt("book_id");
//                books.add(getById(bookId));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }



    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }

//    private Book getBook(ResultSet resultSet) {
//        try {
//            int id = resultSet.getInt("book_id");
//            String title = resultSet.getString("title");
//            int yearIssue = resultSet.getInt("publicationYear");
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
