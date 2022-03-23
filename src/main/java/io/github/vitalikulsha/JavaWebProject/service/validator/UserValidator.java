package io.github.vitalikulsha.JavaWebProject.service.validator;

import io.github.vitalikulsha.JavaWebProject.entity.User;

public class UserValidator implements EntityValidator<User> {

    @Override
    public boolean validate(User entity) {
        return false;
    }
}
