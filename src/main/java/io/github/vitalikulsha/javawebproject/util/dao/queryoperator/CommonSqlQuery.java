package io.github.vitalikulsha.javawebproject.util.dao.queryoperator;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;

public class CommonSqlQuery {
    public final String SQL_SELECT_ALL;
    public final String SQL_SELECT_ALL_PAGE;
    public final String SQL_SELECT_BY_ID;
    public final String SQL_DELETE_BY_ID;
    public final String SQL_SELECT_COUNT_ALL;

    public CommonSqlQuery(Table table, Column columnId) {
        SQL_SELECT_ALL = String.format("SELECT * FROM %s ORDER BY %s", table, columnId);
        SQL_SELECT_ALL_PAGE = String.format("SELECT * FROM %s ORDER BY %s LIMIT ?, ?", table, columnId);
        SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE %s=?", table, columnId);
        SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s=?", table, columnId);
        SQL_SELECT_COUNT_ALL = String.format("SELECT COUNT(*) FROM %s", table);
    }
}
