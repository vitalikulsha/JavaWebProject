package io.github.vitalikulsha.JavaWebProject;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DaoFactory factory = DaoFactory.instance();

//        test bookDao
//        BookDao bookDao = factory.bookDao();
//        List<Book> books = bookDao.getAll();
//        books.forEach(System.out::println);
//        Book book = bookDao.getById(12300);
//        System.out.println(book);
//        List<Book> booksByTitle = bookDao.getByName("Кратк");
//        booksByTitle.forEach(System.out::println);
//        List<Book> booksByCategoryName = bookDao.getByCategoryName("наук");
//        booksByCategoryName.forEach(System.out::println);
//        List<Book> booksByAuthorName = bookDao.getByAuthorName("Хок");
//        booksByAuthorName.forEach(System.out::println);
//        List<Book> booksByAuthorId = bookDao.getByAuthorId(5);
//        booksByAuthorId.forEach(System.out::println);
//        List<Book> booksByCategoryId = bookDao.getByCategoryId(80);
//        booksByCategoryId.forEach(System.out::println);

//        test recordBookDao
        RecordBookDao recordBookDao = factory.recordBookDao();
//        System.out.println(recordBookDao.getById(30001));
//        List<RecordBook> catalogs = recordBookDao.getAll();
//        catalogs.forEach(System.out::println);
//        List<RecordBook> catalogsByName = recordBookDao.getByBookTitle("1984");
//        catalogsByName.forEach(System.out::println);
//        List<RecordBook> catalogsByAuthorName = recordBookDao.getByAuthorName("Хок");
//        catalogsByAuthorName.forEach(System.out::println);
//        List<RecordBook> catalogsByCategoryName = recordBookDao.getByCategoryName("наук");
//        catalogsByCategoryName.forEach(System.out::println);

//        test userDao
//        UserDao userDao = factory.userDao();
//        User userById = userDao.getById(4);
//        System.out.println(userById);
//        List<User> users = userDao.getAll();
//        users.forEach(System.out::println);
//        User userByLogin = userDao.getByLogin("Admin");
//        System.out.println(userByLogin);
//        int maxId = userDao.maxId();
//        System.out.println("maxId = " + maxId);
//        System.out.println(userDao.isExist("User", "user"));
//        System.out.println(userDao.isExist("User", "admin"));

//          test orderDao
//        OrderDao orderDao = factory.orderDao();
//        Order order = orderDao.getById(1);
//        System.out.println(order);
//        List<Order> orders = orderDao.getAll();
//        orders.forEach(System.out::println);

//        test AuthorDao
//        AuthorDao authorDao = factory.authorDao();
//        List<Author> authorsByBookId = authorDao.getAuthorsByBookId(20002);
//        authorsByBookId.forEach(System.out::println);

//        BookSqlQuery query = new BookSqlQuery();
//        System.out.println(query.FIND_BY_TITLE);
    }
}