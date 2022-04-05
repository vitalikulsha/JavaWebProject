package io.github.vitalikulsha.javawebproject.service.validator;

import io.github.vitalikulsha.javawebproject.entity.User;

/**
 * Factory, that provides validator.
 */
public class ValidatorFactory {
    private final static ValidatorFactory instance = new ValidatorFactory();

    private final static EntityValidator<User> userValidator = new UserValidator();

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
