package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class OrderSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_USER_ID;
    public final String SAVE;
    public final String UPDATE_APPROVAL;

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
        FIND_BY_USER_ID = String.format("SELECT * FROM %s WHERE %s=?",
                Table.ORDER_BOOK, Column.USER_ID);
        SAVE = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ?, ?, ?, ?",
                Table.ORDER_BOOK, Column.BOOK_ID, Column.USER_ID, Column.RESERVED, Column.APPROVED);
        UPDATE_APPROVAL = String.format("UPDATE %s SET %s=? WHERE %s=?",
                Table.ORDER_BOOK, Column.APPROVED, Column.ORDER_ID);
    }
}
