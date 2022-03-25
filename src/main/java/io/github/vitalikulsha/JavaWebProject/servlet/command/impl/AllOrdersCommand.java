package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;
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
            log.error("Unable to get all orders", e);
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
