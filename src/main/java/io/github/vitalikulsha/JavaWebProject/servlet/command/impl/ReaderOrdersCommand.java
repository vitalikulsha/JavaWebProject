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
        OrderService orderService = ServiceFactory.instance().orderService();
        if (method.equals(Value.POST)) {
            int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
            orderService.updateOrderReserveStatus(ReserveStatus.RETURN, orderId);
            orderService.updateOrderApproval(false, orderId);
            return new CommandInfo(UserPath.READER_ORDERS.getPath(), RoutingType.REDIRECT);
        }
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
}
