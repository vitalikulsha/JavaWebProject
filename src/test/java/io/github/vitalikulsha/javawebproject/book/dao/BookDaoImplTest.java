package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.Pagination;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void findByBookTitle() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        Pagination<Book> pagination = new Pagination<>(1, ConfigParameter.ITEMS_ON_PAGE);
        List<Book> bookList = DataBase.BOOK_TABLE;
        List<Book> fullTitleBookList = bookList.stream()
                .filter(b -> b.getTitle().equals("Гномы"))
                .collect(Collectors.toList());
        List<Book> partTitleBookList = bookList.stream()
                .filter(b -> b.getTitle().contains("и"))
                .collect(Collectors.toList());
        assertEquals(pagination.paginate(fullTitleBookList),
                bookDao.findByBookTitle(0, ConfigParameter.ITEMS_ON_PAGE, "Гномы"));
        assertNotEquals(pagination.paginate(fullTitleBookList),
                bookDao.findByBookTitle(0, ConfigParameter.ITEMS_ON_PAGE, "Тест"));
        pagination.setFirstIndex(2);
        int fromIndex = pagination.getFirstIndexFrom(2);
        assertEquals(pagination.paginate(partTitleBookList),
                bookDao.findByBookTitle(fromIndex, ConfigParameter.ITEMS_ON_PAGE, "и"));
        assertTrue(bookDao.findByBookTitle(0, 5, "Test").isEmpty());
    }

    @Test
    public void findByAuthorName() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        Pagination<Book> pagination = new Pagination<>(1, ConfigParameter.ITEMS_ON_PAGE);
        List<Book> fullAuthorNameBookList = new ArrayList<>() {{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
        }};
        assertEquals(pagination.paginate(fullAuthorNameBookList),
                bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE, "Хокинг"));
        assertNotEquals(pagination.paginate(fullAuthorNameBookList),
                bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE,"Тест"));
        assertTrue(bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE,"Test").isEmpty());
        List<Book> partAuthorNameBookList = new ArrayList<>(){{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
            this.add(new Book(41001, "Организация охотничьего хозяйства: учебное пособие", 2016, 268, 40, 4));
            this.add(new Book(50002, "Практическая психосоматика", 2019, 324, 50, 2));
            this.add(new Book(60001, "Загадки истории", 2011, 320, 60, 1));
            this.add(new Book(71001, "Твое здоровье в твоих руках", 1973, 56, 70, 4));
            this.add(new Book(82001, "Преступление и наказание", 2019, 608, 80, 4));
            this.add(new Book(90002, "Афоризмы для умных мужчин", 2021, 192, 90, 0));
        }};
        pagination.setFirstIndex(2);
        int fromIndex = pagination.getFirstIndexFrom(2);
        assertEquals(pagination.paginate(partAuthorNameBookList),
                bookDao.findByAuthorName(fromIndex, ConfigParameter.ITEMS_ON_PAGE, "и"));
    }

    @Test
    public void findByCategoryName() {

    }

    @Test
    public void update() {
    }

    @Test
    public void countBySearchParam() {
    }


}