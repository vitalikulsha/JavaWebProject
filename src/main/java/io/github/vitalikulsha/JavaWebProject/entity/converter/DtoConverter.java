package io.github.vitalikulsha.JavaWebProject.entity.converter;

/**
 * DTO converter interface
 *
 * @param <T> DTO type
 * @param <E> entity type
 */

public interface DtoConverter<T, E> {
    /**
     * Convert entity to DTO object
     *
     * @param entity entity
     * @return DTO object
     */
    T toDto(E entity);
}
