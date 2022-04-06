package io.github.vitalikulsha.javawebproject.util.dtoconverter;

import io.github.vitalikulsha.javawebproject.book.entity.BookDTOConverter;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTOConverter;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTOConverter;

/**
 * Factory, that provides DTO converters.
 */
public class DTOConverterFactory {
    private final static DTOConverterFactory instance = new DTOConverterFactory();

    private final BookDTOConverter bookDtoConverter = new BookDTOConverter();
    private final OrderDTOConverter orderDtoConverter = new OrderDTOConverter();
    private final UserDTOConverter userDtoConverter = new UserDTOConverter();

    /**
     * Gets instance.
     *
     * @return instance of DtoConverterFactory
     */
    public static DTOConverterFactory instance() {
        return instance;
    }

    public BookDTOConverter bookDtoConverter() {
        return bookDtoConverter;
    }

    public OrderDTOConverter orderDtoConverter() {
        return orderDtoConverter;
    }

    public UserDTOConverter userDtoConverter() {
        return userDtoConverter;
    }
}
