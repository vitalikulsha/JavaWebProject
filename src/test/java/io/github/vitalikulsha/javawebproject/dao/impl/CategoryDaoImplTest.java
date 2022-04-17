package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.category.entity.Category;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoImplTest {

////    @Test
//    public void findById() throws DaoException {
//        CategoryDao categoryDao = DaoFactory.instance().categoryDao();
//        Category expected = new Category(10, "Энциклопедии");
//        assertEquals(expected, categoryDao.findById(10));
//        assertNotEquals(expected, categoryDao.findById(20));
//        assertNull(categoryDao.findById(11));
//        assertNotNull(categoryDao.findById(30));
//    }
//
////    @Test
//    public void findAll() throws DaoException {
//        CategoryDao categoryDao = DaoFactory.instance().categoryDao();
//        assertEquals(getAllCategories(), categoryDao.findAll());
//    }
//
////    @Test
//    public void deleteById() throws DaoException {
//        CategoryDao categoryDao = DaoFactory.instance().categoryDao();
//        assertEquals(0, categoryDao.deleteById(1));
//        assertEquals(1, categoryDao.deleteById(10));
//    }

    private List<Category> getAllCategories() {
        return new ArrayList<>() {{
            this.add(new Category(10, "Энциклопедии"));
            this.add(new Category(20, "Естественные науки"));
            this.add(new Category(30, "Техника. Технические науки"));
            this.add(new Category(40, "Сельское и лесное хозяйство"));
            this.add(new Category(50, "Здравоохранение. Медицинские науки"));
            this.add(new Category(60, "Социальные и гуманитарные науки"));
            this.add(new Category(70, "Культура. Наука. Просвещение"));
            this.add(new Category(80, "Филологические науки. Художественная литература"));
            this.add(new Category(90, "Литература универсального содержания"));
        }};
    }
}