package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoImplTest {
    BookDao bookDao;
    Paging<Book> paging;
    List<Book> bookList;

    @Before
    public void init() {
        bookDao = DaoFactory.instance().bookDao();
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        bookList = DataBase.BOOK_TABLE;
    }

    @Test
    public void findByBookTitle() throws DaoException {
        List<Book> fullTitleBookList = bookList.stream()
                .filter(b -> b.getTitle().equals("Гномы"))
                .collect(Collectors.toList());
        List<Book> partTitleBookList = bookList.stream()
                .filter(b -> b.getTitle().contains("и"))
                .collect(Collectors.toList());
        assertEquals(paging.paginate(fullTitleBookList),
                bookDao.findByBookTitle(0, ConfigParameter.ITEMS_ON_PAGE, "Гномы"));
        assertNotEquals(paging.paginate(fullTitleBookList),
                bookDao.findByBookTitle(0, ConfigParameter.ITEMS_ON_PAGE, "гномы"));
        paging.setFirstIndex(2);
        int fromIndex = paging.getFirstIndexFrom(2);
        assertEquals(paging.paginate(partTitleBookList),
                bookDao.findByBookTitle(fromIndex, ConfigParameter.ITEMS_ON_PAGE, "и"));
        assertTrue(bookDao.findByBookTitle(0, 5, "Test").isEmpty());
    }

    @Test
    public void findByAuthorName() throws DaoException {
        List<Book> fullAuthorNameBookList = new ArrayList<>() {{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
        }};
        assertEquals(paging.paginate(fullAuthorNameBookList),
                bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE, "Хокинг"));
        assertNotEquals(paging.paginate(fullAuthorNameBookList),
                bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE, "Тест"));
        assertTrue(bookDao.findByAuthorName(0, ConfigParameter.ITEMS_ON_PAGE, "Test").isEmpty());
        List<Book> partAuthorNameBookList = new ArrayList<>() {{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
            this.add(new Book(41001, "Организация охотничьего хозяйства: учебное пособие", 2016, 268, 40, 4));
            this.add(new Book(50002, "Практическая психосоматика", 2019, 324, 50, 2));
            this.add(new Book(60001, "Загадки истории", 2011, 320, 60, 1));
            this.add(new Book(71001, "Твое здоровье в твоих руках", 1973, 56, 70, 4));
            this.add(new Book(82001, "Преступление и наказание", 2019, 608, 80, 4));
            this.add(new Book(90002, "Афоризмы для умных мужчин", 2021, 192, 90, 0));
        }};
        paging.setFirstIndex(2);
        int fromIndex = paging.getFirstIndexFrom(2);
        assertEquals(paging.paginate(partAuthorNameBookList),
                bookDao.findByAuthorName(fromIndex, ConfigParameter.ITEMS_ON_PAGE, "и"));
    }

    @Test
    public void findByCategoryName() throws DaoException {
        List<Book> partCategoryNameBookList = bookList.stream()
                .filter(b -> b.getCategoryId() == 20 || b.getCategoryId() == 30 || b.getCategoryId() == 50
                        || b.getCategoryId() == 60 || b.getCategoryId() == 80)
                .collect(Collectors.toList());
        paging.setFirstIndex(2);
        int fromIndex = paging.getFirstIndexFrom(2);
        assertEquals(paging.paginate(partCategoryNameBookList),
                bookDao.findByCategoryName(fromIndex, ConfigParameter.ITEMS_ON_PAGE, "науки"));
        assertNotEquals(paging.paginate(partCategoryNameBookList),
                bookDao.findByCategoryName(0, ConfigParameter.ITEMS_ON_PAGE, "Наука"));
        assertTrue(bookDao.findByCategoryName(0, ConfigParameter.ITEMS_ON_PAGE, "Test").isEmpty());
    }

    @Test
    public void findAll() throws DaoException {
        assertEquals(bookList, bookDao.findAll());
        assertEquals(paging.paginate(bookList),
                bookDao.findAll(0, ConfigParameter.ITEMS_ON_PAGE));
        paging.setFirstIndex(3);
        int fromIndex = paging.getFirstIndexFrom(3);
        assertEquals(paging.paginate(bookList),
                bookDao.findAll(fromIndex, ConfigParameter.ITEMS_ON_PAGE));
    }

    @Test
    public void findById() throws DaoException {
        Book expected = bookList.stream()
                .filter(b -> b.getId() == 11200)
                .findFirst()
                .get();
        assertEquals(expected, bookDao.findById(11200));
        assertNotEquals(expected, bookDao.findById(10100));
        assertNull(bookDao.findById(10000));
        assertNotNull(bookDao.findById(12300));
    }

    @Test
    public void countAllAndBySearchParam() throws DaoException {
        long expected = bookList.stream()
                .filter(b -> b.getTitle().contains("и"))
                .count();
        assertEquals(expected, bookDao.countBySearchParam(Column.TITLE, "и"));
        assertEquals(0, bookDao.countBySearchParam(Column.TITLE, "Test"));
        assertEquals(bookList.size(), bookDao.countAll());
    }

    @Test
    public void updateAndDelete() throws DaoException {
        Book book = bookList.get(0);
        book.setTitle("Title");
        assertEquals(1, bookDao.update(book));
        assertEquals(book, bookDao.findById(book.getId()));
        book.setId(1);
        assertEquals(0, bookDao.update(book));
        assertEquals(1, bookDao.deleteById(90001));
        assertNull(bookDao.findById(90001));
        assertEquals(0, bookDao.deleteById(1));
    }
}