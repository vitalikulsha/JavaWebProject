package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Pagination;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoImplTest {

    OrderDao orderDao;
    Pagination<Order> pagination;
    List<Order> orderList;

    @Before
    public void init() {
        orderDao = DaoFactory.instance().orderDao();
        pagination = new Pagination<>(1, ConfigParameter.ITEMS_ON_PAGE);
        orderList = DataBase.ORDER_TABLE;
    }

    @Test
    public void findByUserId() throws DaoException {
        List<Order> expected = orderList.stream()
                .filter(o -> o.getUserId() == 3)
                .collect(Collectors.toList());
        assertEquals(expected, orderDao.findByUserId(3));
        assertNotEquals(expected, orderDao.findByUserId(4));
        assertTrue(orderDao.findByUserId(10).isEmpty());
    }

    @Test
    public void findAll() throws DaoException {
        assertEquals(orderList, orderDao.findAll());
        assertEquals(pagination.paginate(orderList),
                orderDao.findAll(0, ConfigParameter.ITEMS_ON_PAGE));
        assertTrue(orderDao.findAll(10, ConfigParameter.ITEMS_ON_PAGE).isEmpty());
    }

    @Test
    public void findById() throws DaoException {
        OrderDao orderDao = DaoFactory.instance().orderDao();
        Order expected = orderList.stream()
                .filter(o -> o.getId() == 1)
                .findFirst()
                .get();
        assertEquals(expected, orderDao.findById(1));
        assertNotEquals(expected, orderDao.findById(2));
        assertNull(orderDao.findById(10));
        assertNotNull(orderDao.findById(3));
    }

    @Test
    public void countAll() throws DaoException {
        assertEquals(orderList.size(), orderDao.countAll());
    }

    @Test
    public void updateMethods() throws DaoException {
        Order saveOrder = new Order(5, 71001, 4, ReserveStatus.READING_ROOM, false);
        Order updateOrder = orderList.get(0);
        updateOrder.setReserveStatus(ReserveStatus.LOANED);
        updateOrder.setApproved(false);
        assertEquals(1, orderDao.update(updateOrder));
        assertEquals(0, orderDao.update(saveOrder));
        assertEquals(updateOrder, orderDao.findById(updateOrder.getId()));

        assertEquals(1, orderDao.save(saveOrder));
        assertEquals(saveOrder, orderDao.findById(5));

        assertEquals(1, orderDao.deleteById(5));
        assertNull(orderDao.findById(5));
        assertEquals(0, orderDao.deleteById(20));
    }
}