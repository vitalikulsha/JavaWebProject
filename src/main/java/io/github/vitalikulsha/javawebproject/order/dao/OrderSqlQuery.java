package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class OrderSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_USER_ID;
    public final String FIND_BY_USER_ID_PAGE;
    public final String SAVE;
    public final String UPDATE;

    public OrderSqlQuery() {
        super(Table.ORDER_BOOK.name(), Column.ORDER_ID.name());
        FIND_BY_USER_ID = String.format("SELECT * FROM %s WHERE %s=? ORDER BY %s",
                Table.ORDER_BOOK, Column.USER_ID, Column.ORDER_ID);
        FIND_BY_USER_ID_PAGE = String.format("SELECT * FROM %s WHERE %s=? ORDER BY %s LIMIT ?, ?",
                Table.ORDER_BOOK, Column.USER_ID, Column.ORDER_ID);
        SAVE = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ?, ?, ?, ?",
                Table.ORDER_BOOK, Column.BOOK_ID, Column.USER_ID, Column.RESERVED, Column.APPROVED);
        UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                Table.ORDER_BOOK, Column.BOOK_ID, Column.USER_ID, Column.RESERVED, Column.APPROVED, Column.ORDER_ID);
    }
}
