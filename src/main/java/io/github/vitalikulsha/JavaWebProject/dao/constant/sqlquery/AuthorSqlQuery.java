package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class AuthorSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_BOOK_ID;

    public AuthorSqlQuery() {
        super(Table.AUTHOR.name(), Column.AUTHOR_ID.name());
        this.FIND_BY_BOOK_ID = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s=%s.%s WHERE %s=?",
                Table.BOOK_AUTHOR, Table.AUTHOR, Table.BOOK_AUTHOR, Column.AUTHOR_ID,
                Table.AUTHOR, Column.AUTHOR_ID, Column.BOOK_ID);
    }
}
