package io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery;

import io.github.vitalikulsha.javawebproject.dao.query.constant.Column;
import io.github.vitalikulsha.javawebproject.dao.query.constant.Table;

public class UserSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_LOGIN;
    public final String FIND_BY_ROLE;
    public final String FIND_BY_EMAIL;
    public final String SAVE;
    public final String UPDATE;

    public UserSqlQuery() {
        super(Table.USER.name(), Column.USER_ID.name());
        FIND_BY_LOGIN = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.LOGIN);
        FIND_BY_ROLE = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.ROLE);
        FIND_BY_EMAIL = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.EMAIL);
        SAVE = String.format("INSERT INTO %s ( %s, %s, %s, %s, %s, %s, %s) VALUES ?, ?, ?, ?, ?, ?, ?",
                Table.USER, Column.LOGIN, Column.PASSWORD, Column.FIRSTNAME, Column.LASTNAME,
                Column.PHONENUMBER, Column.EMAIL, Column.ROLE);
        UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                Table.USER, Column.FIRSTNAME, Column.LASTNAME, Column.PHONENUMBER, Column.EMAIL, Column.USER_ID);
    }
}
