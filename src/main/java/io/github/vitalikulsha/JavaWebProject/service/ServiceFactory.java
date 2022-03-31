package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.service.impl.BookServiceImpl;
import io.github.vitalikulsha.JavaWebProject.service.impl.OrderServiceImpl;
import io.github.vitalikulsha.JavaWebProject.service.impl.UserServiceImpl;

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
     *
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
