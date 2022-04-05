package io.github.vitalikulsha.javawebproject.author.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class AuthorSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_BOOK_ID;

    public AuthorSqlQuery() {
        super(Table.AUTHOR.name(), Column.AUTHOR_ID.name());
        FIND_BY_BOOK_ID = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE %s=?",
                Table.BOOK_AUTHOR, Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID,
                Table.AUTHOR, Column.AUTHOR_ID, Column.BOOK_ID);
    }
}
