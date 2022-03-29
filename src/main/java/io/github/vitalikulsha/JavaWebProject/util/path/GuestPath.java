package io.github.vitalikulsha.JavaWebProject.util.path;

public enum GuestPath {
    LOCALE("/locale"),
    LOGIN("/login"),
    REGISTER("/register");

    private final String path;

    GuestPath(String path) {
        this.path = path;
    }

    public String getPage() {
        return path;
    }
}
