package io.github.vitalikulsha.JavaWebProject.util.path;

public enum GuestPath {
    LOGIN("/login"),
    REGISTER("/register"),
    NOT_FOUND("/not-found");

    private final String path;

    GuestPath(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
