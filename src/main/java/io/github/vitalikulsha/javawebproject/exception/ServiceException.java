package io.github.vitalikulsha.javawebproject.exception;

/**
 * Class to catch service exception.
 */
public class ServiceException extends Exception {

    /**
     * Instantiates a new service exception with specific message
     *
     * @param message exception message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new service exception with specific message and cause
     *
     * @param message   exception message
     * @param cause     reason for the exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
