package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class OrderSqlQuery extends AbstractSqlQuery{

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
    }
}
