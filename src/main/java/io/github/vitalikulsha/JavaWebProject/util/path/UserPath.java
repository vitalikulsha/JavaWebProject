package io.github.vitalikulsha.JavaWebProject.util.path;

public enum UserPath {
    LOGIN("/login"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    NOT_FOUND("/not-found"),
    READER("/reader"),
    BOOK_SEARCH("/reader/book-search"),
    CATALOG("/reader/catalog"),
    ORDER("/reader/order"),
    READER_ORDERS("/reader/reader-orders");

    private final String path;

    UserPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
