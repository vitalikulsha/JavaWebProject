package io.github.vitalikulsha.javawebproject.util.service;

import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.book.service.BookServiceImpl;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.order.service.OrderServiceImpl;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.user.service.UserServiceImpl;

/**
 * Factory, that provides services.
 */
public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final static BookService bookService = new BookServiceImpl();
    private final static OrderService orderService = new OrderServiceImpl();
    private final static UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     * @return instance of ServiceFactory
     */
    public static ServiceFactory instance(){
        return instance;
    }

    public BookService bookService() {
        return bookService;
    }

    public OrderService orderService(){
        return orderService;
    }

    public UserService userService(){
        return userService;
    }
}
