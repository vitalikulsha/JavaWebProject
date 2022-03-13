package io.github.vitalikulsha.JavaWebProject.util.page;

public enum AdminPages{
    LOGIN("/login"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    NOT_FOUND("/not-found"),
    ADMIN("/admin"),
    ALL_ORDERS("/admin/all-orders"),
    ALL_READERS("/admin/all-readers"),
    BOOK_INFO("/admin/book-info"),
    READER_INFO("/admin/reader-info"),
    ORDER_INFO("/admin/order-info");

    private final String path;

    AdminPages(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
