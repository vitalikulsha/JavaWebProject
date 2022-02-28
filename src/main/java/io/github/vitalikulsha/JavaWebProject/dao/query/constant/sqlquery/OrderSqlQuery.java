package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class OrderSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_USER_ID;

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
        FIND_BY_USER_ID = String.format("SELECT * FROM %s WHERE %s=?",
                Table.ORDER_BOOK, Column.USER_ID);
    }
}
