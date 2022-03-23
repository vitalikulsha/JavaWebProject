package io.github.vitalikulsha.JavaWebProject.service.validator;

public interface EntityValidator<T> {
    boolean validate(T entity);
}
