package io.github.vitalikulsha.JavaWebProject.util.path;

/**
 * Servlet path for user with guest role.
 */
public enum GuestPath {
    ERROR_403("/error-403"),
    ERROR_403_IMG("/img/403-error.png"),
    ERROR_404("/error-404"),
    ERROR_404_IMG("/img/404-error.png"),
    ERROR_500("/error-500"),
    ERROR_500_IMG("/img/500-error.png"),
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
