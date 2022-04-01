package io.github.vitalikulsha.JavaWebProject.util.path;

/**
 * Servlet path for user with user role.
 */
public enum UserPath {
    BOOK_SEARCH("/reader/book-search"),
    CATALOG("/reader/catalog"),
    EDIT("/reader/edit"),
    ERROR_403("/error-403"),
    ERROR_403_IMG("/img/403-error.png"),
    ERROR_404("/error-404"),
    ERROR_404_IMG("/img/404-error.png"),
    ERROR_500("/error-500"),
    ERROR_500_IMG("/img/500-error.png"),
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
