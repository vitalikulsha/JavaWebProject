package io.github.vitalikulsha.javawebproject.util.service.validator;

import io.github.vitalikulsha.javawebproject.user.entity.User;

/**
 * Factory, that provides validator.
 */
public class ValidatorFactory {
    private static final ValidatorFactory instance = new ValidatorFactory();

    private static final EntityValidator<User> userValidator = new UserValidator();

    private ValidatorFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of ValidatorFactory
     */
    public static ValidatorFactory instance() {
        return instance;
    }

    public EntityValidator<User> userValidator() {
        return userValidator;
    }
}
