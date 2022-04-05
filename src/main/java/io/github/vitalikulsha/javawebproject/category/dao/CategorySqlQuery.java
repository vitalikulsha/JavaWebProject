package io.github.vitalikulsha.javawebproject.category.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class CategorySqlQuery extends AbstractSqlQuery {

    public CategorySqlQuery() {
        super(Table.CATEGORY.name(), Column.CATEGORY_ID.name());
    }
}
