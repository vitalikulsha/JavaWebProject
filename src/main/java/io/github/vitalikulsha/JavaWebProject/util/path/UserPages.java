package io.github.vitalikulsha.JavaWebProject.util.path;

public enum UserPages {
    BOOK_SEARCH("/reader/book-search"),
    BOOK_CATALOG("/reader/book-catalog"),
    ORDER("/reader/order"),
    LOGIN("/login");

    private String path;

    UserPages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
