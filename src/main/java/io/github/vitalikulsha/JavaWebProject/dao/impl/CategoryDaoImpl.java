package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AbstractDao;
import io.github.vitalikulsha.JavaWebProject.dao.CategoryDao;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.CategorySqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    private final static CategorySqlQuery categorySqlQuery = SqlQueryFactory.instance().categorySqlQuery();

    public CategoryDaoImpl() {
        super(RowMapperFactory.instance().categoryRowMapper(), categorySqlQuery.FIND_ALL, categorySqlQuery.FIND_BY_ID);
    }
}
