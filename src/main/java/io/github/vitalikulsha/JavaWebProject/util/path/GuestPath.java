package io.github.vitalikulsha.JavaWebProject.util.path;

public enum GuestPath {
    LOCALE("/locale"),
    LOGIN("/login"),
    REGISTER("/register");

    private final String path;

    GuestPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
