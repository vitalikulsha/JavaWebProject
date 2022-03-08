package io.github.vitalikulsha.JavaWebProject.util.page;

public enum AdminPages{
    LOGIN("/login"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    NOT_FOUND("/not-found"),
    ADMIN("/admin");

    private final String path;

    AdminPages(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
