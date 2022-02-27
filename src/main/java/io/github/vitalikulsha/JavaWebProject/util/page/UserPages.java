package io.github.vitalikulsha.JavaWebProject.util.page;

public enum UserPages{
    LOGIN("/login"),
    LOGOUT("/logout"),
    NOT_FOUND("/not-found"),
    READER("/reader"),
    BOOK_SEARCH("/reader/book-search"),
//    RECORD_BOOK("/reader/record-book"),
    CATALOG("/reader/catalog"),
    ORDER("/reader/order");

    private final String page;

    UserPages(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
