package io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery;

public abstract class AbstractSqlQuery {
    public final String FIND_ALL;
    public final String FIND_BY_ID;
    public final String DELETE_BY_ID;

    protected AbstractSqlQuery(String table, String columnId) {
        FIND_ALL = String.format("SELECT * FROM %s ORDER BY %s", table, columnId);
        FIND_BY_ID =  String.format("SELECT * FROM %s WHERE %s=?", table, columnId);
        DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s=?", table, columnId);
    }
}
