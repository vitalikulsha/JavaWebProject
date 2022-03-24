package io.github.vitalikulsha.JavaWebProject.service.validator;

public class ValidationPattern {
    public final static String EMAIL_PATTERN = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public final static String NAME_PATTERN = "^([А-Я][а-яё]{1,29}|[A-Z][a-z]{1,29})$";
    public final static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$";
    public static final String PHONE_PATTERN = "^375[\\d]{9}$";
    public static final String LOGIN_PATTERN = "^[A-Za-z0-9._-]{3,19}$";

    private ValidationPattern() {
    }
}
