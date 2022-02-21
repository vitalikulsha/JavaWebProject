package io.github.vitalikulsha.JavaWebProject.util.path;

public enum AdminPages {
    LOGIN("/login");

    private String path;

    AdminPages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
