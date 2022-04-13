package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.CommonSqlQuery;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private static final String SQL_SELECT_BY_TITLE = "SELECT * FROM book" +
            " WHERE title LIKE '%%%s%%' ORDER BY book_id LIMIT ?, ?";
    private static final String SQL_SELECT_BY_AUTHOR_NAME = "SELECT * FROM book b" +
            " INNER JOIN book_author b_a ON b.book_id=b_a.book_id" +
            " INNER JOIN author a ON b_a.author_id=a.author_id" +
            " WHERE a.lastname LIKE '%%%s%%' ORDER BY book_id LIMIT ?, ?";
    private static final String SQL_SELECT_BY_CATEGORY_NAME = "SELECT * FROM book b" +
            " INNER JOIN category c ON b.category=c.category_id" +
            " WHERE c.name LIKE '%%%s%%' ORDER BY book_id LIMIT ?, ?";
    private static final String SQL_UPDATE = "UPDATE book" +
            " SET title=?, publicationyear=?, numberpages=?, category=?, quantity=?" +
            " WHERE book_id=?";
    private static final String SQL_SELECT_COUNT_BY_COLUMN = "SELECT COUNT(DISTINCT book_id) FROM book b" +
            " INNER JOIN book_author b_a ON b.book_id=b_a.book_id " +
            " INNER JOIN author a ON b_a.author_id=a.author_id" +
            " INNER JOIN category c ON b.category=c.category_id" +
            " WHERE %s LIKE '%%%s%%'";

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(),
                new CommonSqlQuery(Table.BOOK, Column.BOOK_ID));
    }

    @Override
    public List<Book> findByBookTitle(int fromIndex, int itemsOnPage, String title) throws DaoException {
        String sqlQuery = String.format(SQL_SELECT_BY_TITLE, title);
        return queryOperator.executeEntityListQuery(sqlQuery, fromIndex, itemsOnPage);
    }

    @Override
    public List<Book> findByAuthorName(int fromIndex, int itemsOnPage, String name) throws DaoException {
        String sqlQuery = String.format(SQL_SELECT_BY_AUTHOR_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery, fromIndex, itemsOnPage);
    }

    @Override
    public List<Book> findByCategoryName(int fromIndex, int itemsOnPage, String name) throws DaoException {
        String sqlQuery = String.format(SQL_SELECT_BY_CATEGORY_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery, fromIndex, itemsOnPage);
    }

    @Override
    public int update(Book book) throws DaoException {
        return queryOperator.executeUpdate(SQL_UPDATE, book.getTitle(), book.getPublicationYear(),
                book.getNumberPages(), book.getCategoryId(), book.getQuantity(), book.getId());
    }

    @Override
    public int countBySearchParam(Column column, String searchParam) throws DaoException {
        String sqlQuery = String.format(SQL_SELECT_COUNT_BY_COLUMN, column, searchParam);
        return queryOperator.executeCountQuery(sqlQuery);
    }
}
