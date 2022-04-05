package io.github.vitalikulsha.javawebproject.category.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.category.entity.Category;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    private final static CategorySqlQuery categorySqlQuery = SqlQueryFactory.instance().categorySqlQuery();

    public CategoryDaoImpl() {
        super(RowMapperFactory.instance().categoryRowMapper(),
                categorySqlQuery.FIND_ALL, categorySqlQuery.FIND_BY_ID, categorySqlQuery.DELETE_BY_ID);
    }
}
