package io.github.vitalikulsha.JavaWebProject.service.validator;

import io.github.vitalikulsha.JavaWebProject.entity.User;

public class UserValidator implements EntityValidator<User> {

    @Override
    public boolean validate(User user) {
        return user != null
                && user.getFirstName() != null
                && user.getFirstName().matches(ValidationPattern.NAME_PATTERN)
                && user.getLastName() != null
                && user.getLastName().matches(ValidationPattern.NAME_PATTERN)
                && user.getEmail() != null
                && user.getEmail().matches(ValidationPattern.EMAIL_PATTERN)
                && user.getPhoneNumber() != 0
                && String.valueOf(user.getPhoneNumber()).matches(ValidationPattern.PHONE_PATTERN);
    }
}
