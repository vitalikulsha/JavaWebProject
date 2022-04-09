package io.github.vitalikulsha.javawebproject.user.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.AbstractSqlQuery;

public class UserSqlQuery extends AbstractSqlQuery {
    public final String FIND_BY_LOGIN;
    public final String FIND_BY_ROLE;
    public final String FIND_BY_ROLE_PAGE;
    public final String FIND_BY_EMAIL;
    public final String SAVE;
    public final String UPDATE;
    public final String COUNT_BY_ROLE;

    public UserSqlQuery() {
        super(Table.USER.name(), Column.USER_ID.name());
        FIND_BY_LOGIN = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.LOGIN);
        FIND_BY_ROLE = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.ROLE);
        FIND_BY_ROLE_PAGE = String.format("SELECT * FROM %s WHERE %s=? LIMIT ?, ?", Table.USER, Column.ROLE);
        FIND_BY_EMAIL = String.format("SELECT * FROM %s WHERE %s=?", Table.USER, Column.EMAIL);
        SAVE = String.format("INSERT INTO %s ( %s, %s, %s, %s, %s, %s, %s) VALUES ?, ?, ?, ?, ?, ?, ?",
                Table.USER, Column.LOGIN, Column.PASSWORD, Column.FIRSTNAME, Column.LASTNAME,
                Column.PHONENUMBER, Column.EMAIL, Column.ROLE);
        UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                Table.USER, Column.LOGIN, Column.PASSWORD, Column.FIRSTNAME, Column.LASTNAME,
                Column.PHONENUMBER, Column.EMAIL, Column.ROLE, Column.USER_ID);
        COUNT_BY_ROLE = String.format("SELECT COUNT(*) FROM %s WHERE %s=?",
                Table.USER, Column.ROLE);
    }
}
