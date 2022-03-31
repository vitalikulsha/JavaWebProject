package io.github.vitalikulsha.JavaWebProject.service.validator;

/**
 * Entity validator class
 * @param <T> entity type
 */
public interface EntityValidator<T> {
    /**
     * Validates the entity
     *
     * @param entity entity
     * @return true if the entity validated
     */
    boolean validate(T entity);
}
