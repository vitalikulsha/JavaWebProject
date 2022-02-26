package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.BookSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    private final static BookSqlQuery bookSqlQuery = SqlQueryFactory.instance().bookSqlQuery();

    public BookDaoImpl() {
        super(RowMapperFactory.instance().bookRowMapper(), bookSqlQuery.FIND_ALL, bookSqlQuery.FIND_BY_ID);
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
