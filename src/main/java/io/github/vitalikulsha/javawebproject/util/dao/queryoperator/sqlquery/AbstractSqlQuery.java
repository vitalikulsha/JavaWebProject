package io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery;

/**
 * Abstract query class
 */
public abstract class AbstractSqlQuery {
    public final String FIND_ALL;
    public final String FIND_BY_ID;
    public final String DELETE_BY_ID;
    public final String COUNT_ALL;

    /**
     * Constructor - creates a new SQL query object.
     *
     * @param table    the name of the table in the database
     * @param columnId column id in table
     */
    protected AbstractSqlQuery(String table, String columnId) {
        FIND_ALL = String.format("SELECT * FROM %s ORDER BY %s", table, columnId);
        FIND_BY_ID = String.format("SELECT * FROM %s WHERE %s=?", table, columnId);
        DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s=?", table, columnId);
        COUNT_ALL = String.format("SELECT COUNT(*) FROM %s", table);
    }
}
