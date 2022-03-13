package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Table;

public class UserSqlQuery extends AbstractSqlQuery{
    public final String FIND_BY_LOGIN;
    public final String FIND_BY_ROLE;

    public UserSqlQuery() {
        super(Table.USER.name(), Column.USER_ID.name());
        FIND_BY_LOGIN = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.LOGIN);
        FIND_BY_ROLE = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.ROLE);
    }
}
