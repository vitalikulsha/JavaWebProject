package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class UserSqlQuery {
    public final static String FIND_ALL = String.format("SELECT * FROM %s", Table.USER);
    public final static String FIND_BY_ID = String.format("SELECT * FROM %s WHERE %s=%%d", Table.USER, Column.USER_ID);
    public final static String FIND_BY_LOGIN = String.format("SELECT * FROM %s WHERE %s=%%s", Table.USER, Column.LOGIN);
    public final static String FIND_MAX_ID = String.format("SELECT MAX(%s) FROM %s", Column.USER_ID, Table.USER);

    private UserSqlQuery() {
    }
}
