package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReaderOrdersCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(Value.GET)) {
            return getCommandInfoGet(request, session);
        } else if(method.equals(Value.POST)){
            return getCommandInfoPost(request);
        }
        return null;
    }

    private CommandInfo getCommandInfoGet(HttpServletRequest request, HttpSession session) {
        OrderService orderService = ServiceFactory.instance().orderService();
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        List<OrderDto> orders = orderService.getOrdersByUserId(user.getId());
        String page = request.getParameter(Parameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        Pagination<OrderDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        List<Integer> pages = pagination.getPageNumbers(orders);
        orders = pagination.getItemsPerPage(orders, pageNumber);
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.USER_ORDERS, orders);
        return new CommandInfo(Page.READER_ORDERS, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) {
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