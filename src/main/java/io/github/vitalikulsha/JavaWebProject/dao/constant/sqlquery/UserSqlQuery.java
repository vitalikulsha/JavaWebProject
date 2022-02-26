package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Table;

public class UserSqlQuery extends AbstractSqlQuery{
    public final String FIND_BY_LOGIN;
    public final String FIND_MAX_ID;

    public UserSqlQuery() {
        super(Table.USER.name(), Column.USER_ID.name());
        this.FIND_BY_LOGIN = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.LOGIN);
        this.FIND_MAX_ID = String.format("SELECT MAX(%s) FROM %s", Column.USER_ID, Table.USER);
    }
}
