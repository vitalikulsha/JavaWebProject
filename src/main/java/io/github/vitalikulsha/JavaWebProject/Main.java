package io.github.vitalikulsha.JavaWebProject;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.*;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DaoFactory factory = DaoFactory.instance();
        ServiceFactory service = ServiceFactory.instance();

//        test bookDao
//        BookDao bookDao = factory.bookDao();
//        List<Book> books = bookDao.getAll();
//        books.forEach(System.out::println);
//        Book book = bookDao.getById(12300);
//        System.out.println(book);
//        List<Book> booksByTitle = bookDao.getByBookTitle("Кратк");
//        booksByTitle.forEach(System.out::println);
//        List<Book> booksByCategoryName = bookDao.getByCategoryName("наук");
//        booksByCategoryName.forEach(System.out::println);
//        List<Book> booksByAuthorName = bookDao.getByAuthorName("Хок");
//        booksByAuthorName.forEach(System.out::println);

//        test BookService
//        BookService bookService = service.bookService();
//        System.out.println(bookService.getById(12300));
//        List<BookDto> booksAll = bookService.getAll();
//        booksAll.forEach(System.out::println);
//        System.out.println(bookService.getCountRow());
//        Pagination<BookDto> pagination = new Pagination<>(5);
//        List<BookDto> booksPagination = pagination.getItemsPerPage(booksAll, 3);
//        booksPagination.forEach(System.out::println);

//        test userDao
        UserDao userDao = factory.userDao();
        User userById = userDao.findById(4);
        System.out.println(userById);
//        List<User> users = userDao.getAll();
//        users.forEach(System.out::println);
//        User userByLogin = userDao.getByLogin("Admin");
//        System.out.println(userByLogin);
//        int maxId = userDao.maxId();
//        System.out.println("maxId = " + maxId);
//        System.out.println(userDao.isExist("User", "user"));
//        System.out.println(userDao.isExist("User", "admin"));
//        List<User> users = userDao.findByRole(Role.USER);
//        users.forEach(System.out::println);

//          test orderDao
        OrderDao orderDao = factory.orderDao();
//        Order order = orderDao.findById(1);
//        System.out.println(order);
//        List<Order> orders = orderDao.findAll();
//        orders.forEach(System.out::println);
        List<Order> ordersByUserId = orderDao.findByUserId(4);
        ordersByUserId.forEach(System.out::println);
//        System.out.println(orderDao.maxId());
//        Order order = new Order(9, 80001, 3, ReserveStatus.LOANED, true);
//        System.out.println(order);
//        System.out.println(orderDao.save(order));
//        List<Order> orders = orderDao.findAll();
//        orders.forEach(System.out::println);

//        test orderService
//        OrderService orderService = ServiceFactory.instance().orderService();
//        OrderDto orderDto = orderService.getById(4);
//        System.out.println(orderDto);
//        List<OrderDto> ordersDto = orderService.getAll();
//        ordersDto.forEach(System.out::println);
//        List<OrderDto> orderDtoByUserId = orderService.getOrderByUserId(3);
//        orderDtoByUserId.forEach(System.out::println);

//        test AuthorDao
//        AuthorDao authorDao = factory.authorDao();
//        List<Author> authorsByBookId = authorDao.getAuthorsByBookId(20002);
//        authorsByBookId.forEach(System.out::println);

//        test userService
        UserService userService = ServiceFactory.instance().userService();
        UserDto userDto = DtoConverterFactory.instance().userDtoConverter().toDto(userById);
        System.out.println(userDto);
//        UserDto userDtoById = userService.getById(3);
//        System.out.println(userDtoById);
    }
}