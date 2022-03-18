package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

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
        return orderDao.save(order) == 1;
    }

    @Override
    public void delete(OrderDto orderDto) {

    }
}
