package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.dao.BookDao;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.BookSqlQuery;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private final static BookSqlQuery bookSqlQuery = SqlQueryFactory.instance().bookSqlQuery();

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(),
                bookSqlQuery.FIND_ALL, bookSqlQuery.FIND_BY_ID, bookSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Book> findByBookTitle(String title) throws DaoException {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_TITLE, title);
    }

    @Override
    public List<Book> findByAuthorName(String name) throws DaoException {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_AUTHOR_NAME, name);
    }

    @Override
    public List<Book> findByCategoryName(String name) throws DaoException {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_CATEGORY_NAME, name);
    }

    @Override
    public int updateQuantityBooks(int quantityBooks, int bookId) throws DaoException {
        return queryOperator.executeUpdate(bookSqlQuery.UPDATE_QUANTITY, quantityBooks, bookId);
    }
}
