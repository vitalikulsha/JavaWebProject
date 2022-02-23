package io.github.vitalikulsha.JavaWebProject.util.page;

public enum GuestPages{
    LOGIN("/login"),
    REGISTER("/register"),
    NOT_FOUND("/not-found");

    private final String page;

    GuestPages(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
