package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class OrderSqlQuery {
    public final static String FIND_ALL =
            String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s INNER JOIN %s ON %s.%s=%s.%s",
                    Table.ORDER_BOOK, Table.BOOK, Table.ORDER_BOOK, Column.BOOK_ID,
                    Table.BOOK, Column.BOOK_ID, Table.USER, Table.ORDER_BOOK, Column.USER_ID,
                    Table.USER, Column.USER_ID);
    public final static String FIND_BY_ID =
            String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s INNER JOIN %s ON %s.%s=%s.%s WHERE %s=%%d",
                    Table.ORDER_BOOK, Table.BOOK, Table.ORDER_BOOK, Column.BOOK_ID,
                    Table.BOOK, Column.BOOK_ID, Table.USER, Table.ORDER_BOOK, Column.USER_ID,
                    Table.USER, Column.USER_ID, Column.ORDER_ID);

    private OrderSqlQuery() {
    }
}
