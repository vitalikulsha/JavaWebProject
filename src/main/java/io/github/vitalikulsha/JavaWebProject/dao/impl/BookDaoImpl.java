package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.BookSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
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
    public List<Book> findByBookTitle(String title) {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_TITLE, title);
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_AUTHOR_NAME, name);
    }

    @Override
    public List<Book> findByCategoryName(String name) {
        return queryOperator.executeEntityListQueryWithLikeParam(bookSqlQuery.FIND_BY_CATEGORY_NAME, name);
    }
}
