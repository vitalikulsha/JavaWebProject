package io.github.vitalikulsha.JavaWebProject.dto;

public class DtoConverterFactory {
    private final static DtoConverterFactory instance = new DtoConverterFactory();

    private final BookDtoConverter bookDtoConverter = new BookDtoConverter();
    private final OrderDtoConverter orderDtoConverter = new OrderDtoConverter();
    private final UserDtoConverter userDtoConverter = new UserDtoConverter();

    public static DtoConverterFactory instance(){
        return instance;
    }

    public BookDtoConverter bookDtoConverter(){
        return bookDtoConverter;
    }

    public OrderDtoConverter orderDtoConverter(){
        return orderDtoConverter;
    }

    public UserDtoConverter userDtoConverter(){
        return userDtoConverter;
    }
}
