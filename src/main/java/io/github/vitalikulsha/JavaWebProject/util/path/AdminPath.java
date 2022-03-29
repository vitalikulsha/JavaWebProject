package io.github.vitalikulsha.JavaWebProject.util.path;

public enum AdminPath {
    ADMIN("/admin"),
    ALL_BOOKS("/admin/all-books"),
    ALL_ORDERS("/admin/all-orders"),
    ALL_READERS("/admin/all-readers"),
    BOOK_INFO("/admin/book-info"),
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
