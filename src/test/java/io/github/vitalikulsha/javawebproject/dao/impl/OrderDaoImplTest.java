package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.order.dao.OrderDao;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void findByUserId() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        List<Order> expected = getAllOrders().stream()
                .filter(o -> o.getUserId() == 3)
                .collect(Collectors.toList());
        assertEquals(expected, orderDao.findByUserId(3));
        assertNotEquals(expected, orderDao.findByUserId(4));
        assertTrue(orderDao.findByUserId(10).isEmpty());
    }

    @Test
    public void save() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        Order saveOrder = new Order(5, 71001, 4, ReserveStatus.READING_ROOM, false);
        assertEquals(1, orderDao.save(saveOrder));
        assertEquals(saveOrder, orderDao.findById(5));
    }

    @Test
    public void findById() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        Order expected = getAllOrders().stream()
                .filter(o -> o.getId() == 1)
                .findFirst()
                .get();
        assertEquals(expected, orderDao.findById(1));
        assertNotEquals(expected, orderDao.findById(2));
        assertNull(orderDao.findById(10));
        assertNotNull(orderDao.findById(3));
    }

    @Test
    public void findAll() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        assertEquals(getAllOrders(), orderDao.findAll());
    }

    @Test
    public void deleteById() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        assertEquals(1, orderDao.deleteById(1));
        assertNull(orderDao.findById(1));
        assertEquals(0, orderDao.deleteById(10));
    }

    private List<Order> getAllOrders() {
        return new ArrayList<>() {{
            this.add(new Order(1, 90002, 3, ReserveStatus.READING_ROOM, true));
            this.add(new Order(2, 50001, 4, ReserveStatus.LOANED, false));
            this.add(new Order(3, 82001, 3, ReserveStatus.LOANED, true));
            this.add(new Order(4, 71001, 3, ReserveStatus.READING_ROOM, false));
        }};
    }
}