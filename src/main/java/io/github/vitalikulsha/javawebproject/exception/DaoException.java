package io.github.vitalikulsha.javawebproject.exception;

/**
 * Class to catch DAO exception.
 */
public class DaoException extends Exception {

    /**
     * Instantiates a new DAO exception with specific message
     *
     * @param message exception message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new DAO exception with specific message and cause
     *
     * @param message exception message
     * @param cause   reason for the exception
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
