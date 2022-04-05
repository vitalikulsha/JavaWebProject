package io.github.vitalikulsha.javawebproject.entity.converter;

import io.github.vitalikulsha.javawebproject.dao.*;
import io.github.vitalikulsha.javawebproject.entity.Book;
import io.github.vitalikulsha.javawebproject.entity.Order;
import io.github.vitalikulsha.javawebproject.entity.User;
import io.github.vitalikulsha.javawebproject.entity.dto.BookDto;
import io.github.vitalikulsha.javawebproject.entity.dto.OrderDto;
import io.github.vitalikulsha.javawebproject.entity.dto.UserDto;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDtoConverter implements DtoConverter<OrderDto, Order> {

    @Override
    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        UserDao userDao = DaoFactory.instance().userDao();
        BookDao bookDao = DaoFactory.instance().bookDao();
        DtoConverter<BookDto, Book> bookDtoConverter = DtoConverterFactory.instance().bookDtoConverter();
        DtoConverter<UserDto, User> userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
        BookDto bookDto;
        UserDto userDto;
        try {
            Book book = bookDao.findById(order.getBookId());
            bookDto = bookDtoConverter.toDto(book);
            User user = userDao.findById(order.getUserId());
            userDto = userDtoConverter.toDto(user);
        } catch (DaoException e) {
            log.error("DaoException: user or/and book is null");
            return null;
        }
        return new OrderDto.Builder()
                .fixId(order.getId())
                .fixBookDto(bookDto)
                .fixUser(userDto)
                .fixReserveStatus(order.getReserveStatus())
                .fixApproved(order.getApproved())
                .build();
    }
}
