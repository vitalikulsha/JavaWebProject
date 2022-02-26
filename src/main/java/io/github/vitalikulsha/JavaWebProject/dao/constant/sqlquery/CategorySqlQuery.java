package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class CategorySqlQuery extends AbstractSqlQuery {

    public CategorySqlQuery() {
        super(Table.CATEGORY.name(), Column.CATEGORY_ID.name());
    }
}
