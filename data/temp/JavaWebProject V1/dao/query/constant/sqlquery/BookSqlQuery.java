package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class BookSqlQuery extends AbstractSqlQuery {

    public BookSqlQuery() {
        super(Table.BOOK.name(), Column.BOOK_ID.name());
    }
}
