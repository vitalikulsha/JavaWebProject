package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.order.entity.dto.OrderDto;
import io.github.vitalikulsha.javawebproject.user.entity.dto.UserDto;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.Attribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.Parameter;
import io.github.vitalikulsha.javawebproject.util.constant.Value;
import io.github.vitalikulsha.javawebproject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
public class ReaderOrdersCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(Value.GET)) {
            try {
                return getCommandInfoGet(request, session);
            } catch (ServiceException e) {
                log.error("Unable to get orders by user id: "  + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (method.equals(Value.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable to update user: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.ERROR_403, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoGet(HttpServletRequest request, HttpSession session) throws ServiceException {
        OrderService orderService = ServiceFactory.instance().orderService();
        Pagination<OrderDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(Attribute.URL, url);
        List<OrderDto> orders = orderService.getOrdersByUserId(user.getId());
        pagination.paginate(orders, request, Attribute.USER_ORDERS);
        return new CommandInfo(Page.READER_ORDERS, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        OrderService orderService = ServiceFactory.instance().orderService();
        int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
        String action = request.getParameter(Parameter.ACTION);
        if (action.equals(Value.REFUND)) {
            orderService.updateOrderReserveStatus(ReserveStatus.REFUND, orderId);
        } else if (action.equals(Value.CANCEL)) {
            orderService.deleteById(orderId);
        }
        return new CommandInfo(UserPath.READER_ORDERS.getPath(), RoutingType.REDIRECT);
    }
}