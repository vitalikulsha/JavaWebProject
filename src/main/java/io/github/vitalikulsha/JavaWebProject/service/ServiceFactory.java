package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.service.impl.BookServiceImpl;
import io.github.vitalikulsha.JavaWebProject.service.impl.OrderServiceImpl;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final static BookService bookService = new BookServiceImpl();
    private final static OrderService orderService = new OrderServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory instance(){
        return instance;
    }

    public BookService bookService() {
        return bookService;
    }

    public OrderService orderService(){
        return orderService;
    }
}
