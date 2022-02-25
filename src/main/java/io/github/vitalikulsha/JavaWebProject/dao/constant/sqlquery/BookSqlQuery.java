package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class BookSqlQuery {
    public final static String FIND_ALL =
            String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s",
                    Table.BOOK, Table.CATEGORY, Table.BOOK, Column.CATEGORY, Table.CATEGORY, Column.CATEGORY_ID);
    public final static String FIND_BY_ID =
            String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE %s=?",
                    Table.BOOK, Table.CATEGORY, Table.BOOK, Column.CATEGORY, Table.CATEGORY, Column.CATEGORY_ID,
                    Column.BOOK_ID);

    private BookSqlQuery() {
    }
}
