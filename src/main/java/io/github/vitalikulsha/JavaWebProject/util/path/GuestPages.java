package io.github.vitalikulsha.JavaWebProject.util.path;

public enum GuestPages{
    LOGIN("/login"),
    NOT_FOUND("/not-found");

    private final String path;

    GuestPages(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
