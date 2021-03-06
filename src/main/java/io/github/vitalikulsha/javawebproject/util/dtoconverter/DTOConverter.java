package io.github.vitalikulsha.javawebproject.util.dtoconverter;

/**
 * DTO converter interface
 *
 * @param <T> the type of the entity DTO being cast to
 * @param <E> the type of entity being converted
 */

public interface DTOConverter<T, E> {
    /**
     * Converts an entity to a DTO object.
     *
     * @param entity the type of entity being converted
     * @return converted entity DTO
     */
    T toDto(E entity);
}
