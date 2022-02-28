package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;

import java.util.List;

public interface OrderService extends Service<OrderDto> {
    List<OrderDto> getOrderByUserId(int userId);
}
