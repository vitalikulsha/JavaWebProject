package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorDaoImplTest {

////    @Test
//    public void findAuthorsByBookId() throws DaoException {
//        AuthorDao authorDao = DaoFactory.instance().authorDao();
//        List<Author> expected = new ArrayList<>() {{
//            this.add(new Author(1, "Бернар", "Вербер"));
//        }};
//        assertEquals(expected, authorDao.findAuthorsByBookId(10100));
//        assertNotEquals(expected, authorDao.findAuthorsByBookId(11200));
//        assertTrue(authorDao.findAuthorsByBookId(10000).isEmpty());
//        assertFalse(authorDao.findAuthorsByBookId(90002).isEmpty());
//    }
//
////    @Test
//    public void findById() throws DaoException {
//        AuthorDao authorDao = DaoFactory.instance().authorDao();
//        Author expected = new Author(1, "Бернар", "Вербер");
//        assertEquals(expected, authorDao.findById(1));
//        assertNotEquals(expected, authorDao.findById(2));
//        assertNull(authorDao.findById(28));
//        assertNotNull(authorDao.findById(27));
//    }
//
////    @Test
//    public void findAll() throws DaoException {
//        AuthorDao authorDao = DaoFactory.instance().authorDao();
//        assertEquals(getAllAuthors(), authorDao.findAll());
//    }
//
////    @Test
//    public void deleteById() throws DaoException {
//        AuthorDao authorDao = DaoFactory.instance().authorDao();
//        assertEquals(0, authorDao.deleteById(30));
//        assertEquals(1, authorDao.deleteById(10));
//    }

    private List<Author> getAllAuthors() {
        return new ArrayList<>() {{
            this.add(new Author(1, "Бернар", "Вербер"));
            this.add(new Author(2, "Кирилл", "Королёв"));
            this.add(new Author(3, "Тим", "Аппензеллер"));
            this.add(new Author(4, "Юрий", "Федосюк"));
            this.add(new Author(5, "Стивен", "Хокинг"));
            this.add(new Author(6, "Роджер", "Пенроуз"));
            this.add(new Author(7, "Микаэль", "Лонэ"));
            this.add(new Author(8, "Анастасия", "Мартюшева"));
            this.add(new Author(9, "Борис", "Черпаков"));
            this.add(new Author(10, "Петер", "Нойферт"));
            this.add(new Author(11, "Людвиг", "Нефф"));
            this.add(new Author(12, "Александр", "Чаянов"));
            this.add(new Author(13, "Юрий", "Мальков"));
            this.add(new Author(14, "Евгений", "Чешуин"));
            this.add(new Author(15, "Эдвард", "Радзинский"));
            this.add(new Author(16, "Михаил", "Марченко"));
            this.add(new Author(17, "Татьяна", "Колотова"));
            this.add(new Author(18, "Юрий", "Чикоров"));
            this.add(new Author(19, "Освальд", "Шпенглер"));
            this.add(new Author(20, "Степан", "Кривенков"));
            this.add(new Author(21, "Александр", "Афанасьев"));
            this.add(new Author(22, "Джордж", "Оруэлл"));
            this.add(new Author(23, "Эрих Мария", "Ремарк"));
            this.add(new Author(24, "Федор", "Достоевский"));
            this.add(new Author(25, "Василь", "Быков"));
            this.add(new Author(26, "Омар", "Хайам"));
            this.add(new Author(27, "Марк", "Фалкирк"));
        }};
    }
}