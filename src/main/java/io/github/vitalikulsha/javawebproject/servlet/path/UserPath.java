package io.github.vitalikulsha.javawebproject.servlet.path;

/**
 * Servlet path for user with user role.
 */
public enum UserPath {
    BOOK_SEARCH("/reader/book-search"),
    CATALOG("/reader/catalog"),
    EDIT("/reader/edit"),
    LOGOUT("/logout"),
    ORDER("/reader/order"),
    READER("/reader"),
    READER_ORDERS("/reader/reader-orders"),
    READER_ORDER_INFO("/reader/reader-order-info");

    private final String path;

    UserPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
