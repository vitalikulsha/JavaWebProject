package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class BookSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_TITLE;
    public final String FIND_BY_CATEGORY_NAME;
    public final String FIND_BY_AUTHOR_NAME;
    public final String UPDATE_NUMBER;

    public BookSqlQuery() {
        super(Table.BOOK.name(), Column.BOOK_ID.name());
        String PATTERN = "%%s%";
        FIND_BY_TITLE = String.format("SELECT * FROM %s WHERE title LIKE '%%%s%%'", Table.BOOK, PATTERN);
        FIND_BY_CATEGORY_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE name LIKE '%%%s%%'",
                Table.BOOK, Table.CATEGORY, Table.BOOK, Column.CATEGORY, Table.CATEGORY, Column.CATEGORY_ID, PATTERN);
        FIND_BY_AUTHOR_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s" +
                        " WHERE %s LIKE '%%%s%%'",
                Table.BOOK, Table.BOOK_AUTHOR, Table.BOOK, Column.BOOK_ID, Table.BOOK_AUTHOR, Column.BOOK_ID,
                Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID, Table.AUTHOR, Column.AUTHOR_ID,
                Column.LASTNAME, PATTERN);
        UPDATE_NUMBER = String.format("UPDATE %s SET %s=? WHERE %s=?",
                Table.BOOK, Column.NUMBER, Column.BOOK_ID);
    }
}
