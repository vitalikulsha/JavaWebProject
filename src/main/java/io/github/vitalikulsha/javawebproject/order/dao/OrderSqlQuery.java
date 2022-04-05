package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class OrderSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_USER_ID;
    public final String SAVE;
    public final String UPDATE_APPROVAL;
    public final String UPDATE_RESERVED;

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
        FIND_BY_USER_ID = String.format("SELECT * FROM %s WHERE %s=? ORDER BY %s",
                Table.ORDER_BOOK, Column.USER_ID, Column.ORDER_ID);
        SAVE = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ?, ?, ?, ?",
                Table.ORDER_BOOK, Column.BOOK_ID, Column.USER_ID, Column.RESERVED, Column.APPROVED);
        UPDATE_APPROVAL = String.format("UPDATE %s SET %s=? WHERE %s=?",
                Table.ORDER_BOOK, Column.APPROVED, Column.ORDER_ID);
        UPDATE_RESERVED = String.format("UPDATE %s SET %s=? WHERE %s=?",
                Table.ORDER_BOOK, Column.RESERVED, Column.ORDER_ID);
    }
}
