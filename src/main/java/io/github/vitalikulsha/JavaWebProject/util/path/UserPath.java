package io.github.vitalikulsha.JavaWebProject.util.path;

public enum UserPath {
    BOOK_SEARCH("/reader/book-search"),
    CATALOG("/reader/catalog"),
    EDIT("/reader/edit"),
    LOCALE("/locale"),
    LOGIN("/login"),
    LOGOUT("/logout"),
    ORDER("/reader/order"),
    READER("/reader"),
    READER_ORDERS("/reader/reader-orders"),
    READER_ORDER_INFO("/reader/reader-order-info"),
    REGISTER("/register");

    private final String path;

    UserPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
