package io.github.vitalikulsha.javawebproject.order.entity;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }
        UserDao userDao = DaoFactory.instance().userDao();
        BookDao bookDao = DaoFactory.instance().bookDao();
        DTOConverter<BookDTO, Book> bookDTOConverter = DTOConverterFactory.instance().bookDtoConverter();
        DTOConverter<UserDTO, User> userDTOConverter = DTOConverterFactory.instance().userDtoConverter();
        BookDTO bookDto;
        UserDTO userDto;
        try {
            Book book = bookDao.findById(order.getBookId());
            bookDto = bookDTOConverter.toDto(book);
            User user = userDao.findById(order.getUserId());
            userDto = userDTOConverter.toDto(user);
        } catch (DaoException e) {
            log.error("DaoException: user or/and book is null.");
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
