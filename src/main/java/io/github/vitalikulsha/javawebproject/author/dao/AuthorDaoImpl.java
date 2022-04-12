package io.github.vitalikulsha.javawebproject.author.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.CommonSqlQuery;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {

    public AuthorDaoImpl() {
        super(RowMapperFactory.instance().authorRowMapper(),
                new CommonSqlQuery(Table.AUTHOR, Column.AUTHOR_ID));
    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId) throws DaoException {
        String sqlQuery = "SELECT * FROM book_author b_a" +
                " INNER JOIN author a ON b_a.author_id=a.author_id WHERE book_id=?";
        return queryOperator.executeEntityListQuery(sqlQuery, bookId);
    }
}
