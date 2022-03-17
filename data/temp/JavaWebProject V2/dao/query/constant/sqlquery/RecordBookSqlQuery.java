package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class RecordBookSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_TITLE;
    public final String FIND_BY_CATEGORY_NAME;
    public final String FIND_BY_AUTHOR_NAME;

    public RecordBookSqlQuery() {
        super(Table.RECORD_BOOK.name(), Column.BOOK_ID.name());
        String PATTERN = "%%s%";
        FIND_BY_TITLE = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE title LIKE '%%%s%%'",
                Table.RECORD_BOOK, Table.BOOK, Table.RECORD_BOOK, Column.BOOK_ID, Table.BOOK, Column.BOOK_ID, PATTERN);
        FIND_BY_CATEGORY_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s WHERE name LIKE '%%%s%%'",
                Table.RECORD_BOOK, Table.BOOK, Table.RECORD_BOOK, Column.BOOK_ID, Table.BOOK, Column.BOOK_ID,
                Table.CATEGORY, Table.BOOK, Column.CATEGORY, Table.CATEGORY, Column.CATEGORY_ID, PATTERN);
        FIND_BY_AUTHOR_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s" +
                        " WHERE %s LIKE '%%%s%%'",
                Table.RECORD_BOOK, Table.BOOK, Table.RECORD_BOOK, Column.BOOK_ID, Table.BOOK, Column.BOOK_ID,
                Table.BOOK_AUTHOR, Table.BOOK, Column.BOOK_ID, Table.BOOK_AUTHOR, Column.BOOK_ID,
                Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID, Table.AUTHOR, Column.AUTHOR_ID,
                Column.FIRSTNAME, PATTERN);
    }
}
