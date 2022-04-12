package io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery;

import io.github.vitalikulsha.javawebproject.order.dao.OrderSqlQuery;
import io.github.vitalikulsha.javawebproject.user.dao.UserSqlQuery;

/**
 * Factory, that provides SQL query
 */
public class SqlQueryFactory {
    private static final SqlQueryFactory instance = new SqlQueryFactory();

    private static final OrderSqlQuery orderSqlQuery = new OrderSqlQuery();
    private static final UserSqlQuery userSqlQuery = new UserSqlQuery();

    private SqlQueryFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of SqlQueryFactory
     */
    public static SqlQueryFactory instance() {
        return instance;
    }

    public OrderSqlQuery orderSqlQuery() {
        return orderSqlQuery;
    }

    public UserSqlQuery userSqlQuery() {
        return userSqlQuery;
    }
}
