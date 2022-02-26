package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.AuthorDao;
import io.github.vitalikulsha.JavaWebProject.dao.CategoryDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookRowMapper implements RowMapper<Book> {
    private final static DaoFactory factory = DaoFactory.instance();

    @Override
    public Book getEntity(ResultSet resultSet) throws SQLException {
        CategoryDao categoryDao = factory.categoryDao();
        AuthorDao authorDao = factory.authorDao();
        try {
            int id = resultSet.getInt(Column.BOOK_ID.name());
            String title = resultSet.getString(Column.TITLE.name());
            int publicationYear = resultSet.getInt(Column.PUBLICATIONYEAR.name());
            int numberPages = resultSet.getInt(Column.NUMBERPAGES.name());
            int categoryId = resultSet.getInt(Column.CATEGORY.name());
            Category category = categoryDao.getById(categoryId);
            List<Author> authors = authorDao.getAuthorsByBookId(id);
            return new Book(id, title, authors, publicationYear, numberPages, category);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
