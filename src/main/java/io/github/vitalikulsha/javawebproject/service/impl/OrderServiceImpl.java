package io.github.vitalikulsha.javawebproject.service.impl;

import io.github.vitalikulsha.javawebproject.dao.*;
import io.github.vitalikulsha.javawebproject.entity.converter.DtoConverter;
import io.github.vitalikulsha.javawebproject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.javawebproject.entity.dto.OrderDto;
import io.github.vitalikulsha.javawebproject.entity.Order;
import io.github.vitalikulsha.javawebproject.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final DtoConverter<OrderDto, Order> orderDtoConverter;

    public OrderServiceImpl() {
        orderDao = DaoFactory.instance().orderDao();
        orderDtoConverter = DtoConverterFactory.instance().orderDtoConverter();
    }

    @Override
    public OrderDto getById(int id) throws ServiceException {
        try {
            return orderDtoConverter.toDto(orderDao.findById(id));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting order from DB by id", e);
        }
    }

    @Override
    public List<OrderDto> getAll() throws ServiceException {
        try {
            return orderDao.findAll()
                    .stream()
                    .map(orderDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting all orders from DB", e);
        }
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) throws ServiceException {
        try {
            return orderDao.findByUserId(userId)
                    .stream()
                    .map(orderDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting orders from DB by user id", e);
        }
    }

    @Override
    public boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus) throws ServiceException {
        Order order = new Order(0, bookId, userId, reserveStatus, false);
        try {
            return orderDao.save(order) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when creating a new order", e);
        }
    }

    @Override
    public boolean updateOrderApproval(boolean approved, int orderId) throws ServiceException {
        try {
            return orderDao.updateApproved(approved, orderId) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when updating order approval status", e);
        }
    }

    @Override
    public boolean updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId) throws ServiceException {
        try {
            return orderDao.updateReserved(reserveStatus, orderId) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when updating order reserve status", e);
        }
    }

    @Override
    public boolean deleteById(int orderId) throws ServiceException {
        try {
            return orderDao.deleteById(orderId) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when deleting a order", e);
        }
    }
}
