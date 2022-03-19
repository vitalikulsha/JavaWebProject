package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
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
    public OrderDto getById(int id) {
        return orderDtoConverter.toDto(orderDao.findById(id));
    }

    @Override
    public List<OrderDto> getAll() {
        return orderDao.findAll()
                .stream()
                .map(orderDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) {
        return orderDao.findByUserId(userId)
                .stream()
                .map(orderDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus) {
        Order order = new Order(0, bookId, userId, reserveStatus, false);
        return orderDao.save(order) != 0;
    }

    @Override
    public boolean updateOrderApproval(boolean approved, int orderId) {
        return orderDao.updateApproved(approved, orderId) != 0;
    }

    @Override
    public boolean updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId){
        return orderDao.updateReserved(reserveStatus, orderId) != 0;
    }

    @Override
    public boolean deleteById(int orderId) {
        return orderDao.deleteById(orderId) != 0;
    }
}
