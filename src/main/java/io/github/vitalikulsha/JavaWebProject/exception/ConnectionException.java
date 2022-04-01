package io.github.vitalikulsha.JavaWebProject.exception;

/**
 * Class for catching exceptions when connecting to a database.
 */
public class ConnectionException extends Exception {

    /**
     * Instantiates a new database connection exception with a specific message
     *
     * @param message exception message
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new database connection exception with a specific message and cause
     *
     * @param message exception message
     * @param cause   reason for the exception
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
