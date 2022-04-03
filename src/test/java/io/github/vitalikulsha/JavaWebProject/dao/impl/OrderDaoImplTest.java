package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.OrderDao;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    private OrderDao orderDao;

    @Before
    public void init() {
        orderDao = DaoFactory.instance().orderDao();
    }

    @Test
    public void findByUserId() throws DaoException {
        List<Order> expected = getAllOrders().stream()
                .filter(o -> o.getUserId() == 3)
                .collect(Collectors.toList());
        assertEquals(expected, orderDao.findByUserId(3));
        assertNotEquals(expected, orderDao.findByUserId(4));
        assertTrue(orderDao.findByUserId(10).isEmpty());
    }

    @Test
    public void save() throws DaoException {
        Order saveOrder = new Order(5, 71001, 4, ReserveStatus.READING_ROOM, false);
        assertEquals(1, orderDao.save(saveOrder));
        assertEquals(saveOrder, orderDao.findById(5));
    }

    @Test
    public void updateApproved() throws DaoException {
        assertEquals(1, orderDao.updateApproved(true, 4));
        assertTrue(orderDao.findById(4).getApproved());
        assertEquals(0, orderDao.updateApproved(false, 10));
    }

    @Test
    public void updateReserved() throws DaoException {
        assertEquals(1, orderDao.updateReserved(ReserveStatus.REFUND, 2));
        assertSame(orderDao.findById(2).getReserveStatus(), ReserveStatus.REFUND);
        assertEquals(0, orderDao.updateReserved(ReserveStatus.LOANED, 10));
    }

    @Test
    public void findById() throws DaoException {
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
        assertEquals(getAllOrders(), orderDao.findAll());
    }

    @Test
    public void deleteById() throws DaoException {
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