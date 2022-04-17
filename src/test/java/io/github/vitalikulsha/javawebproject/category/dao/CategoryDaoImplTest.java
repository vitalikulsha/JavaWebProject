package io.github.vitalikulsha.javawebproject.category.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.category.entity.Category;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoImplTest {
    CategoryDao categoryDao;
    Paging<Category> paging;
    List<Category> categoryList;

    @Before
    public void init(){
        categoryDao = DaoFactory.instance().categoryDao();
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        categoryList = DataBase.CATEGORY_TABLE;
    }

    @Test
    public void findById() throws DaoException {
        Category expected = categoryList.stream()
                .filter(c -> c.getId() == 10)
                .findFirst()
                .get();
        assertEquals(expected, categoryDao.findById(10));
        assertNotEquals(expected, categoryDao.findById(20));
        assertNull(categoryDao.findById(11));
        assertNotNull(categoryDao.findById(30));
    }

    @Test
    public void findAll() throws DaoException {
        assertEquals(categoryList, categoryDao.findAll());
        assertEquals(paging.paginate(categoryList),
                categoryDao.findAll(0, ConfigParameter.ITEMS_ON_PAGE));
        paging.setFirstIndex(2);
        int fromIndex = paging.getFirstIndexFrom(2);
        assertEquals(paging.paginate(categoryList),
                categoryDao.findAll(fromIndex, ConfigParameter.ITEMS_ON_PAGE));
        assertTrue(categoryDao.findAll(100, ConfigParameter.ITEMS_ON_PAGE).isEmpty());
    }
}