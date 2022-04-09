package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private static final BookSqlQuery bookSqlQuery = SqlQueryFactory.instance().bookSqlQuery();

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(),
                bookSqlQuery.FIND_ALL, bookSqlQuery.FIND_ALL_PAGE, bookSqlQuery.FIND_BY_ID,
                bookSqlQuery.DELETE_BY_ID, bookSqlQuery.COUNT_ALL);
    }

    @Override
    public List<Book> findByBookTitle(int firstIndex, int itemsOnPage, String title) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_TITLE, title);
        return queryOperator.executeEntityListQuery(sqlQuery, firstIndex, itemsOnPage);
    }

    @Override
    public List<Book> findByAuthorName(int firstIndex, int itemsOnPage, String name) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_AUTHOR_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery, firstIndex, itemsOnPage);
    }

    @Override
    public List<Book> findByCategoryName(int firstIndex, int itemsOnPage, String name) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_CATEGORY_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery, firstIndex, itemsOnPage);
    }

    @Override
    public int update(Book book) throws DaoException {
        return queryOperator.executeUpdate(bookSqlQuery.UPDATE, book.getTitle(), book.getPublicationYear(),
                book.getNumberPages(), book.getCategoryId(), book.getQuantity(), book.getId());
    }

    @Override
    public int countBySearchParam(Column column, String searchParam) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.COUNT_BY_PARAM, column, searchParam);
        return queryOperator.executeCountQuery(sqlQuery);
    }
}
