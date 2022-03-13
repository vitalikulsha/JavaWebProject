package io.github.vitalikulsha.JavaWebProject.util.page;

public enum UserPages{
    LOGIN("/login"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    NOT_FOUND("/not-found"),
    READER("/reader"),
    BOOK_SEARCH("/reader/book-search"),
    CATALOG("/reader/catalog"),
    ORDER("/reader/order"),
    READER_ORDERS("/reader/reader-orders");

    private final String page;

    UserPages(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
