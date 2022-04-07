package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private static final BookSqlQuery bookSqlQuery = SqlQueryFactory.instance().bookSqlQuery();

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(),
                bookSqlQuery.FIND_ALL, bookSqlQuery.FIND_BY_ID, bookSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Book> findByBookTitle(String title) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_TITLE, title);
        return queryOperator.executeEntityListQuery(sqlQuery);
    }

    @Override
    public List<Book> findByAuthorName(String name) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_AUTHOR_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery);
    }

    @Override
    public List<Book> findByCategoryName(String name) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.FIND_BY_CATEGORY_NAME, name);
        return queryOperator.executeEntityListQuery(sqlQuery);
    }

    @Override
    public int update(Book book) throws DaoException {
        return queryOperator.executeUpdate(bookSqlQuery.UPDATE, book.getTitle(), book.getPublicationYear(),
                book.getNumberPages(), book.getCategoryId(), book.getQuantity(), book.getId());
    }

    @Override
    public List<Book> findAllPagination(int page, int itemsOnPage) throws DaoException {
        return queryOperator.executeEntityListQuery(bookSqlQuery.FIND_ALL_PAGE, page, itemsOnPage);
    }

    @Override
    public int countBySearchParam(String searchParam, Column column) throws DaoException {
        String sqlQuery = String.format(bookSqlQuery.COUNT, column);
        return queryOperator.executeCountyQuery(sqlQuery, searchParam);
    }
}
