package io.github.vitalikulsha.javawebproject.order.entity;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DtoConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DtoConverterFactory;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDTOConverter implements DtoConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }
        UserDao userDao = DaoFactory.instance().userDao();
        BookDao bookDao = DaoFactory.instance().bookDao();
        DtoConverter<BookDTO, Book> bookDtoConverter = DtoConverterFactory.instance().bookDtoConverter();
        DtoConverter<UserDTO, User> userDtoConverter = DtoConverterFactory.instance().userDtoConverter();
        BookDTO bookDto;
        UserDTO userDto;
        try {
            Book book = bookDao.findById(order.getBookId());
            bookDto = bookDtoConverter.toDto(book);
            User user = userDao.findById(order.getUserId());
            userDto = userDtoConverter.toDto(user);
        } catch (DaoException e) {
            log.error("DaoException: user or/and book is null");
            return null;
        }
        return new OrderDTO.Builder()
                .fixId(order.getId())
                .fixBookDto(bookDto)
                .fixUser(userDto)
                .fixReserveStatus(order.getReserveStatus())
                .fixApproved(order.getApproved())
                .build();
    }
}
