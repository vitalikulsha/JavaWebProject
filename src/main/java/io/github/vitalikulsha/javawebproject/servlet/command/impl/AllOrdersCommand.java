package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.entity.dto.OrderDto;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.service.OrderService;
import io.github.vitalikulsha.javawebproject.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.Attribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class AllOrdersCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.instance().orderService();
        Pagination<OrderDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String url = request.getServletContext().getContextPath() + AdminPath.ALL_ORDERS.getPath() + "?";
        request.setAttribute(Attribute.URL, url);
        try {
            List<OrderDto> allOrders = orderService.getAll();
            pagination.paginate(allOrders, request, Attribute.ALL_ORDERS);
            return new CommandInfo(Page.ALL_ORDERS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get all orders: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
