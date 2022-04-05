package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void findAll() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        assertEquals(getAllBooks(), bookDao.findAll());
    }

    @Test
    public void findByBookTitle() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        List<Book> wholeTitleBookList = getAllBooks().stream()
                .filter(b -> b.getTitle().equals("Гномы"))
                .collect(Collectors.toList());
        List<Book> partTitleBookList = getAllBooks().stream()
                .filter(b -> b.getTitle().contains("Орган"))
                .collect(Collectors.toList());
        assertEquals(wholeTitleBookList, bookDao.findByBookTitle("Гномы"));
        assertNotEquals(wholeTitleBookList, bookDao.findByBookTitle("Тест"));
        assertEquals(partTitleBookList, bookDao.findByBookTitle("Орган"));
        assertTrue(bookDao.findByBookTitle("Test").isEmpty());
    }

    @Test
    public void findByAuthorName() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        List<Book> partAuthorNameBookList = new ArrayList<>() {{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
        }};
        assertEquals(partAuthorNameBookList, bookDao.findByAuthorName("Хок"));
        assertNotEquals(partAuthorNameBookList, bookDao.findByAuthorName("Тест"));
        assertTrue(bookDao.findByAuthorName("Test").isEmpty());
    }

    @Test
    public void findByCategoryName() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        List<Book> partCategoryNameBookList = getAllBooks().stream()
                .filter(b -> b.getCategoryId() == 20 || b.getCategoryId() == 30 || b.getCategoryId() == 50
                        || b.getCategoryId() == 60 || b.getCategoryId() == 80)
                .collect(Collectors.toList());
        assertEquals(partCategoryNameBookList, bookDao.findByCategoryName("науки"));
        assertNotEquals(partCategoryNameBookList, bookDao.findByCategoryName("Наука"));
        assertTrue(bookDao.findByCategoryName("Test").isEmpty());
    }

    @Test
    public void updateQuantityBooks() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
//        assertEquals(1, bookDao.updateQuantityBooks(4, 10100));
//        assertEquals(4, bookDao.findById(10100).getQuantity());
        assertEquals(0, bookDao.updateQuantityBooks(10, 10000));
    }

    @Test
    public void findById() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        Book expected = getAllBooks().stream()
                .filter(b -> b.getId() == 11200)
                .findFirst()
                .get();
        assertEquals(expected, bookDao.findById(11200));
        assertNotEquals(expected, bookDao.findById(10100));
        assertNull(bookDao.findById(10000));
        assertNotNull(bookDao.findById(12300));
    }

    @Test
    public void deleteById() throws DaoException {
        BookDao bookDao = DaoFactory.instance().bookDao();
        assertEquals(1, bookDao.deleteById(10100));
        assertEquals(0, bookDao.deleteById(10000));
    }

    private List<Book> getAllBooks() {
        return new ArrayList<>() {{
            this.add(new Book(10100, "Энциклопедия относительного и абсолютного знания", 2014, 288, 10, 5));
            this.add(new Book(11200, "Толкиен и его мир", 2005, 496, 10, 6));
            this.add(new Book(12300, "Гномы", 2005, 496, 10, 3));
            this.add(new Book(13400, "Что непонятно у классиков, или Энциклопедия русского быта XIX века", 2005, 496, 10, 2));
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
            this.add(new Book(21001, "Большой роман о математике", 2018, 320, 20, 4));
            this.add(new Book(22001, "99 секретов химии", 2020, 224, 20, 6));
            this.add(new Book(30001, "Технологическая оснастка", 2012, 288, 30, 8));
            this.add(new Book(31001, "Проектирование и строительство. Дом, квартира, сад", 2016, 256, 30, 5));
            this.add(new Book(40001, "Организация крестьянского хозяйства", 2015, 363, 40, 6));
            this.add(new Book(41001, "Организация охотничьего хозяйства: учебное пособие", 2016, 268, 40, 4));
            this.add(new Book(50001, "Гомеопатия и не только", 2018, 368, 50, 3));
            this.add(new Book(50002, "Практическая психосоматика", 2019, 324, 50, 2));
            this.add(new Book(60001, "Загадки истории", 2011, 320, 60, 1));
            this.add(new Book(61001, "Теория государства и права", 2013, 652, 60, 10));
            this.add(new Book(70001, "Закат Европы", 2006, 630, 70, 3));
            this.add(new Book(71001, "Твое здоровье в твоих руках", 1973, 56, 70, 4));
            this.add(new Book(80001, "Народные русские сказки", 2018, 280, 80, 5));
            this.add(new Book(81001, "1984", 2015, 320, 80, 6));
            this.add(new Book(81002, "Триумфальная арка", 2017, 640, 80, 7));
            this.add(new Book(82001, "Преступление и наказание", 2019, 608, 80, 4));
            this.add(new Book(82101, "Знак беды", 1989, 542, 80, 3));
            this.add(new Book(90001, "Великие цитаты и афоризмы", 2021, 319, 90, 1));
            this.add(new Book(90002, "Афоризмы для умных мужчин", 2021, 192, 90, 0));
        }};
    }
}