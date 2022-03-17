package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class OrderSqlQuery extends AbstractSqlQuery{

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
    }
}
