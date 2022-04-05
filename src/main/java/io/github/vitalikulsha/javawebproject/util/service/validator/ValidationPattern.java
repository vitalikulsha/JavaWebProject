package io.github.vitalikulsha.javawebproject.util.service.validator;

/**
 * Patterns for validating entity fields.
 */
public class ValidationPattern {
    public final static String EMAIL_PATTERN = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public final static String NAME_PATTERN = "^([А-Я][а-яё]{1,30}|[A-Z][a-z]{1,30})$";
    public final static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$";
    public static final String PHONE_PATTERN = "^375[\\d]{9}$";
    public static final String LOGIN_PATTERN = "^[A-Za-z0-9._-]{3,20}$";

    private ValidationPattern() {
    }
}
