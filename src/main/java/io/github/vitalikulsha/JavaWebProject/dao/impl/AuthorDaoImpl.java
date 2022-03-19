package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.AuthorDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.AuthorSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Author;

import java.util.List;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private final static AuthorSqlQuery authorSqlQuery = SqlQueryFactory.instance().authorSqlQuery();

    public AuthorDaoImpl() {
        super(RowMapperFactory.instance().authorRowMapper(),
                authorSqlQuery.FIND_ALL, authorSqlQuery.FIND_BY_ID, authorSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId) {
        return queryOperator.executeEntityListQueryWithParam(authorSqlQuery.FIND_BY_BOOK_ID, bookId);
    }
}
