package io.github.vitalikulsha.javawebproject.order.service;

import io.github.vitalikulsha.javawebproject.order.dao.OrderDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final DTOConverter<OrderDTO, Order> orderDTOConverter;

    public OrderServiceImpl() {
        orderDao = DaoFactory.instance().orderDao();
        orderDTOConverter = DTOConverterFactory.instance().orderDtoConverter();
    }

    @Override
    public OrderDTO getById(int id) throws ServiceException {
        try {
            return orderDTOConverter.toDto(orderDao.findById(id));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting order from DB by id", e);
        }
    }

    @Override
    public List<OrderDTO> getAll() throws ServiceException {
        try {
            return orderDao.findAll()
                    .stream()
                    .map(orderDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting all orders from DB", e);
        }
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(int userId) throws ServiceException {
        try {
            return orderDao.findByUserId(userId)
                    .stream()
                    .map(orderDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting orders from DB by user id", e);
        }
    }

    @Override
    public void createOrder(int bookId, int userId, ReserveStatus reserveStatus) throws ServiceException {
        Order order = new Order(0, bookId, userId, reserveStatus, false);
        try {
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException("Exception when creating a new order", e);
        }
    }

    @Override
    public void deleteById(int orderId) throws ServiceException {
        try {
            orderDao.deleteById(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception when deleting a order", e);
        }
    }

    @Override
    public void updateOrderApproval(boolean approved, int orderId) throws ServiceException {
        try {
            Order order = orderDao.findById(orderId);
            order.setApproved(approved);
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("Exception when updating order approval status", e);
        }
    }

    @Override
    public void updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId) throws ServiceException {
        try {
            Order order = orderDao.findById(orderId);
            order.setReserveStatus(reserveStatus);
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("Exception when updating order reserve status", e);
        }
    }
}