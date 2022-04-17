package io.github.vitalikulsha.javawebproject.author.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Pagination;
import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoImplTest {
    AuthorDao authorDao;
    Pagination<Author> pagination;
    List<Author> authorList;

    @Before
    public void init() {
        authorDao = DaoFactory.instance().authorDao();
        pagination = new Pagination<>(1, ConfigParameter.ITEMS_ON_PAGE);
        authorList = DataBase.AUTHOR_TABLE;
    }

    @Test
    public void findAuthorsByBookId() throws DaoException {
        List<Author> expected = new ArrayList<>() {{
            this.add(new Author(1, "Бернар", "Вербер"));
        }};
        assertEquals(expected, authorDao.findAuthorsByBookId(10100));
        assertNotEquals(expected, authorDao.findAuthorsByBookId(11200));
        assertTrue(authorDao.findAuthorsByBookId(10000).isEmpty());
        assertFalse(authorDao.findAuthorsByBookId(90002).isEmpty());

    }

    @Test
    public void findAll() throws DaoException {
        assertEquals(authorList, authorDao.findAll());
        assertEquals(pagination.paginate(authorList),
                authorDao.findAll(0, ConfigParameter.ITEMS_ON_PAGE));
        pagination.setFirstIndex(3);
        int fromIndex = pagination.getFirstIndexFrom(3);
        assertEquals(pagination.paginate(authorList),
                authorDao.findAll(fromIndex, ConfigParameter.ITEMS_ON_PAGE));
        assertTrue(authorDao.findAll(100, ConfigParameter.ITEMS_ON_PAGE).isEmpty());
    }

    @Test
    public void findById() throws DaoException {
        Author author = authorList.stream()
                .filter(a -> a.getId()==10)
                .findFirst()
                .get();
        assertEquals(author, authorDao.findById(10));
        assertNotEquals(author, authorDao.findById(1));
        assertNull(authorDao.findById(100));
    }

    @Test
    public void countAll() throws DaoException {
        assertEquals(authorList.size(), authorDao.countAll());
    }
}