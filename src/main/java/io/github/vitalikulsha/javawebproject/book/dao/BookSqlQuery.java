package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class BookSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_TITLE;
    public final String FIND_BY_CATEGORY_NAME;
    public final String FIND_BY_AUTHOR_NAME;
    public final String UPDATE;
    public final String FIND_ALL_PAGE;

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
        UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                Table.BOOK, Column.TITLE, Column.PUBLICATIONYEAR, Column.NUMBERPAGES,
                Column.CATEGORY, Column.QUANTITY, Column.BOOK_ID);
        FIND_ALL_PAGE = String.format("SELECT * FROM %s ORDER BY %s LIMIT ?, ?",
                Table.BOOK, Column.BOOK_ID);
    }
}
