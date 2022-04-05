package io.github.vitalikulsha.javawebproject.util.dtoconverter;

import io.github.vitalikulsha.javawebproject.book.entity.dto.BookDtoConverter;
import io.github.vitalikulsha.javawebproject.user.entity.dto.UserDtoConverter;
import io.github.vitalikulsha.javawebproject.order.entity.dto.OrderDtoConverter;

/**
 * Factory, that provides DTO converters.
 */
public class DtoConverterFactory {
    private final static DtoConverterFactory instance = new DtoConverterFactory();

    private final BookDtoConverter bookDtoConverter = new BookDtoConverter();
    private final OrderDtoConverter orderDtoConverter = new OrderDtoConverter();
    private final UserDtoConverter userDtoConverter = new UserDtoConverter();

    /**
     * Gets instance.
     *
     * @return instance of DtoConverterFactory
     */
    public static DtoConverterFactory instance() {
        return instance;
    }

    public BookDtoConverter bookDtoConverter() {
        return bookDtoConverter;
    }

    public OrderDtoConverter orderDtoConverter() {
        return orderDtoConverter;
    }

    public UserDtoConverter userDtoConverter() {
        return userDtoConverter;
    }
}
