package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class BookSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_TITLE_PAGE;
    public final String FIND_BY_CATEGORY_NAME;
    public final String FIND_BY_CATEGORY_NAME_PAGE;
    public final String FIND_BY_AUTHOR_NAME;
    public final String FIND_BY_AUTHOR_NAME_PAGE;
    public final String UPDATE;
    public final String FIND_ALL_PAGE;
    public final String COUNT_BY_PARAM;

    public BookSqlQuery() {
        super(Table.BOOK.name(), Column.BOOK_ID.name());
        String LIKE_PATTERN = "%%s%";
        String WHERE_PATTERN = "%s";
        FIND_BY_TITLE_PAGE = String.format("SELECT * FROM %s WHERE %s LIKE '%%%s%%' ORDER BY %s LIMIT ?, ?",
                Table.BOOK, Column.TITLE, LIKE_PATTERN, Column.BOOK_ID);
        FIND_BY_CATEGORY_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE %s LIKE '%%%s%%'",
                Table.BOOK, Table.CATEGORY, Table.BOOK, Column.CATEGORY,
                Table.CATEGORY, Column.CATEGORY_ID, Column.NAME, LIKE_PATTERN);
        FIND_BY_CATEGORY_NAME_PAGE = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE %s LIKE '%%%s%%' ORDER BY %s LIMIT ?, ?",
                Table.BOOK, Table.CATEGORY, Table.BOOK, Column.CATEGORY,
                Table.CATEGORY, Column.CATEGORY_ID, Column.NAME, LIKE_PATTERN, Column.BOOK_ID);
        FIND_BY_AUTHOR_NAME = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s" +
                        " WHERE %s LIKE '%%%s%%'",
                Table.BOOK, Table.BOOK_AUTHOR, Table.BOOK, Column.BOOK_ID, Table.BOOK_AUTHOR, Column.BOOK_ID,
                Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID, Table.AUTHOR, Column.AUTHOR_ID,
                Column.LASTNAME, LIKE_PATTERN);
        FIND_BY_AUTHOR_NAME_PAGE = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s" +
                        " INNER JOIN %s ON %s.%s=%s.%s" +
                        " WHERE %s LIKE '%%%s%%' ORDER BY %s LIMIT ?, ?",
                Table.BOOK, Table.BOOK_AUTHOR, Table.BOOK, Column.BOOK_ID, Table.BOOK_AUTHOR, Column.BOOK_ID,
                Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID, Table.AUTHOR, Column.AUTHOR_ID,
                Column.LASTNAME, LIKE_PATTERN, Column.BOOK_ID);
        UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                Table.BOOK, Column.TITLE, Column.PUBLICATIONYEAR, Column.NUMBERPAGES,
                Column.CATEGORY, Column.QUANTITY, Column.BOOK_ID);
        FIND_ALL_PAGE = String.format("SELECT * FROM %s ORDER BY %s LIMIT ?, ?",
                Table.BOOK, Column.BOOK_ID);
        COUNT_BY_PARAM = String.format("SELECT COUNT(DISTINCT %s) FROM %s" +
                        " JOIN %s ON %s.%s=%s.%s " +
                        " JOIN %s ON %s.%s=%s.%s" +
                        " JOIN %s ON %s.%s=%s.%s" +
                        " WHERE %s LIKE '%%%s%%'",
                Column.BOOK_ID, Table.BOOK,
                Table.BOOK_AUTHOR, Table.BOOK, Column.BOOK_ID, Table.BOOK_AUTHOR, Column.BOOK_ID,
                Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID, Table.AUTHOR, Column.AUTHOR_ID,
                Table.CATEGORY, Table.BOOK, Column.CATEGORY, Table.CATEGORY, Column.CATEGORY_ID,
                WHERE_PATTERN, LIKE_PATTERN);
    }
}
