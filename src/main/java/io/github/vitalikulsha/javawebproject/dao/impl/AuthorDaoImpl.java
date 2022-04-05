package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.AuthorSqlQuery;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.entity.Author;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private final static AuthorSqlQuery authorSqlQuery = SqlQueryFactory.instance().authorSqlQuery();

    public AuthorDaoImpl() {
        super(RowMapperFactory.instance().authorRowMapper(),
                authorSqlQuery.FIND_ALL, authorSqlQuery.FIND_BY_ID, authorSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId) throws DaoException {
        return queryOperator.executeEntityListQueryWithParam(authorSqlQuery.FIND_BY_BOOK_ID, bookId);
    }
}
