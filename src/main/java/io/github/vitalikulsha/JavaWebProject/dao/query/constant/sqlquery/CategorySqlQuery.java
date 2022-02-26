package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class CategorySqlQuery extends AbstractSqlQuery {

    public CategorySqlQuery() {
        super(Table.CATEGORY.name(), Column.CATEGORY_ID.name());
    }
}
