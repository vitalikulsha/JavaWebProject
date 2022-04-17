package io.github.vitalikulsha.javawebproject.order.service;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceImplTest {
    OrderService orderService;
    DTOConverter<OrderDTO, Order> dtoConverter;
    List<OrderDTO> orderDTOList;
    Paging<OrderDTO> paging;
    Pagination pagination;

    @Before
    public void init() {
        orderService = ServiceFactory.instance().orderService();
        dtoConverter = DTOConverterFactory.instance().orderDtoConverter();
        orderDTOList = DataBase.ORDER_TABLE.stream()
                .map(o -> dtoConverter.toDto(o))
                .collect(Collectors.toList());
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        pagination = new Pagination(1, ConfigParameter.ITEMS_ON_PAGE);
    }

    @Test
    public void getById() throws ServiceException {
        OrderDTO expected = orderDTOList.stream()
                .filter(o -> o.getId() == 3)
                .findFirst()
                .get();
        assertEquals(expected, orderService.getById(3));
        assertNotEquals(expected, orderService.getById(2));
        assertNull(orderService.getById(10));
    }

    @Test
    public void getAll() throws ServiceException {
        assertEquals(paging.paginate(orderDTOList), orderService.getAll(pagination));
        pagination.setFromIndex(3);
        assertTrue(orderService.getAll(pagination).isEmpty());
    }

    @Test
    public void getOrdersByUserId() throws ServiceException {
        List<OrderDTO> expected = orderDTOList.stream()
                .filter(o -> o.getUserDto().getId() == 3)
                .collect(Collectors.toList());
        assertEquals(expected, orderService.getOrdersByUserId(3));
        assertNotEquals(expected, orderService.getOrdersByUserId(4));
        assertTrue(orderService.getOrdersByUserId(5).isEmpty());
    }

    @Test
    public void updateMethods() throws ServiceException {
        Order newOrder = new Order(5, 71001, 4, ReserveStatus.READING_ROOM, false);
        orderService.createOrder(71001, 4, ReserveStatus.READING_ROOM);
        assertEquals(dtoConverter.toDto(newOrder), orderService.getById(5));
        orderService.deleteById(5);
        assertNull(orderService.getById(5));
        orderService.updateOrderReserveStatus(ReserveStatus.REFUND, 2);
        assertEquals(ReserveStatus.REFUND, orderService.getById(2).getReserveStatus());
        orderService.updateOrderApproval(true, 4);
        assertTrue(orderService.getById(4).getApproved());
    }

    @Test
    public void countAll() throws ServiceException {
        assertEquals(orderDTOList.size(), orderService.countAll());
    }
}