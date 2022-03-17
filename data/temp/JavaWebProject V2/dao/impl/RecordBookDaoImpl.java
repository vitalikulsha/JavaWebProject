package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;

import java.util.List;

public class RecordBookDaoImpl extends AbstractDao<RecordBook> implements RecordBookDao {
    private final static RecordBookSqlQuery recordBookSqlQuery = SqlQueryFactory.instance().recordBookSqlQuery();

    public RecordBookDaoImpl() {
        super(RowMapperFactory.instance().recordBookRowMapper(),
                recordBookSqlQuery.FIND_ALL, recordBookSqlQuery.FIND_BY_ID);
    }

    @Override
    public List<RecordBook> getByBookTitle(String title) {
        return queryOperator.executeEntityListQueryWithLikeParam(recordBookSqlQuery.FIND_BY_TITLE, title);
    }

    @Override
    public List<RecordBook> getByAuthorName(String name) {
        return queryOperator.executeEntityListQueryWithLikeParam(recordBookSqlQuery.FIND_BY_AUTHOR_NAME, name);
    }

    @Override
    public List<RecordBook> getByCategoryName(String name) {
        return queryOperator.executeEntityListQueryWithLikeParam(recordBookSqlQuery.FIND_BY_CATEGORY_NAME, name);
    }

    @Override
    public RecordBook save(RecordBook recordBook) {
        return null;
    }

    @Override
    public void delete(RecordBook recordBook) {

    }
}
