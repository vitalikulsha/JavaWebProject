package io.github.vitalikulsha.JavaWebProject.entity.converter;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;

public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {

    @Override
    public OrderDto toDto(Order order) {
        if(order == null){
            return null;
        }
        UserDao userDao = DaoFactory.instance().userDao();
        BookDao bookDao = DaoFactory.instance().bookDao();
        DtoConverter<BookDto, Book> bookDtoConverter = DtoConverterFactory.instance().bookDtoConverter();
        Book book = bookDao.findById(order.getBookId());
        BookDto bookDto = bookDtoConverter.toDto(book);
        DtoConverter<UserDto, User> userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
        User user = userDao.findById(order.getUserId());
        UserDto userDto = userDtoConverter.toDto(user);
        return new OrderDto.Builder()
                .fixId(order.getId())
                .fixBookDto(bookDto)
                .fixUser(userDto)
                .fixReserveStatus(order.getReserveStatus())
                .fixApproved(order.getApproved())
                .build();
    }
}
