package io.github.vitalikulsha.javawebproject.util.service.validator;

/**
 * Entity validator class.
 *
 * @param <T> entity type in this validator
 */
public interface EntityValidator<T> {
    /**
     * Validates an entity for pattern matching.
     *
     * @param entity entity
     * @return true if the entity matches the pattern
     */
    boolean validate(T entity);
}
