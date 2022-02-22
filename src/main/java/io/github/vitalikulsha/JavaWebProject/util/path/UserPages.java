package io.github.vitalikulsha.JavaWebProject.util.path;

public enum UserPages{
    LOGIN("/login"),
    LOGOUT("/logout"),
    NOT_FOUND("/not-found"),
    BOOK_SEARCH("/reader/book-search"),
    BOOK_CATALOG("/reader/book-catalog"),
    ORDER("/reader/order");

    private final String path;

    UserPages(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
