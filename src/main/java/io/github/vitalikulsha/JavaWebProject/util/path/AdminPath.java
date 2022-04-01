package io.github.vitalikulsha.JavaWebProject.util.path;

/**
 * Servlet path for user with admin role.
 */
public enum AdminPath {
    ADMIN("/admin"),
    ALL_BOOKS("/admin/all-books"),
    ALL_ORDERS("/admin/all-orders"),
    ALL_READERS("/admin/all-readers"),
    BOOK_INFO("/admin/book-info"),
    ERROR_403("/error-403"),
    ERROR_403_IMG("/img/403-error.png"),
    ERROR_404("/error-404"),
    ERROR_404_IMG("/img/404-error.png"),
    ERROR_500("/error-500"),
    ERROR_500_IMG("/img/500-error.png"),
    LOCALE("/locale"),
    LOGIN("/login"),
    LOGOUT("/logout"),
    ORDER_INFO("/admin/order-info"),
    READER_INFO("/admin/reader-info"),
    REGISTER("/register");

    private final String path;

    AdminPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
