package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.CategorySqlQuery;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.entity.Category;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    private final static CategorySqlQuery categorySqlQuery = SqlQueryFactory.instance().categorySqlQuery();

    public CategoryDaoImpl() {
        super(RowMapperFactory.instance().categoryRowMapper(),
                categorySqlQuery.FIND_ALL, categorySqlQuery.FIND_BY_ID, categorySqlQuery.DELETE_BY_ID);
    }
}
