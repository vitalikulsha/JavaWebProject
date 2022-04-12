package io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;

public class CommonSqlQuery {
    public final String SQL_FIND_ALL;
    public final String SQL_FIND_ALL_PAGE;
    public final String SQL_FIND_BY_ID;
    public final String SQL_DELETE_BY_ID;
    public final String SQL_COUNT_ALL;

    public CommonSqlQuery(Table table, Column columnId) {
        SQL_FIND_ALL = String.format("SELECT * FROM %s ORDER BY %s", table, columnId);
        SQL_FIND_ALL_PAGE = String.format("SELECT * FROM %s ORDER BY %s LIMIT ?, ?", table, columnId);
        SQL_FIND_BY_ID = String.format("SELECT * FROM %s WHERE %s=?", table, columnId);
        SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s=?", table, columnId);
        SQL_COUNT_ALL = String.format("SELECT COUNT(*) FROM %s", table);
    }
}
