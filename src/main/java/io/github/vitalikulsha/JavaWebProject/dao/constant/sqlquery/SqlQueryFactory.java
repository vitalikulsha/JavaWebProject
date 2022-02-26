package io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery;

public class SqlQueryFactory {
    private final static SqlQueryFactory instance = new SqlQueryFactory();

    private final static AuthorSqlQuery authorSqlQuery = new AuthorSqlQuery();
    private final static CategorySqlQuery categorySqlQuery = new CategorySqlQuery();
    private final static BookSqlQuery bookSqlQuery = new BookSqlQuery();
    private final static OrderSqlQuery orderSqlQuery = new OrderSqlQuery();
    private final static UserSqlQuery userSqlQuery = new UserSqlQuery();

    private SqlQueryFactory() {
    }

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
