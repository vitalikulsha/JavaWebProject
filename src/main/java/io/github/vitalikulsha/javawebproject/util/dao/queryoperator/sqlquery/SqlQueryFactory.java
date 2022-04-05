package io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorSqlQuery;
import io.github.vitalikulsha.javawebproject.book.dao.BookSqlQuery;
import io.github.vitalikulsha.javawebproject.category.dao.CategorySqlQuery;
import io.github.vitalikulsha.javawebproject.order.dao.OrderSqlQuery;
import io.github.vitalikulsha.javawebproject.user.dao.UserSqlQuery;

/**
 * Factory, that provides SQL query
 */
public class SqlQueryFactory {
    private final static SqlQueryFactory instance = new SqlQueryFactory();

    private final static AuthorSqlQuery authorSqlQuery = new AuthorSqlQuery();
    private final static CategorySqlQuery categorySqlQuery = new CategorySqlQuery();
    private final static BookSqlQuery bookSqlQuery = new BookSqlQuery();
    private final static OrderSqlQuery orderSqlQuery = new OrderSqlQuery();
    private final static UserSqlQuery userSqlQuery = new UserSqlQuery();

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

    public AuthorSqlQuery authorSqlQuery() {
        return authorSqlQuery;
    }

    public CategorySqlQuery categorySqlQuery() {
        return categorySqlQuery;
    }

    public BookSqlQuery bookSqlQuery() {
        return bookSqlQuery;
    }

    public OrderSqlQuery orderSqlQuery() {
        return orderSqlQuery;
    }

    public UserSqlQuery userSqlQuery() {
        return userSqlQuery;
    }
}
