package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.JspValue;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.servlet.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class AllOrdersCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(JspValue.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable to update order status: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        OrderService orderService = ServiceFactory.instance().orderService();
        String url = request.getContextPath() + AdminPath.ALL_ORDERS.getPath() + "?";
        request.setAttribute(SessionAttribute.URL, url);
        String page = request.getParameter(RequestParameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        Pagination pagination = new Pagination(pageNumber, ConfigParameter.ITEMS_ON_PAGE);
        try {
            List<OrderDTO> allOrders = orderService.getAll(pagination);
            List<Integer> pages = pagination.getPages(orderService.countAll());
            request.setAttribute(SessionAttribute.ALL_ORDERS, allOrders);
            request.setAttribute(SessionAttribute.PAGES, pages);
            return new CommandInfo(Page.ALL_ORDERS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get all orders: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.instance().bookService();
        OrderService orderService = ServiceFactory.instance().orderService();
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.ORDER_ID));
        String action = request.getParameter(RequestParameter.ACTION);
        OrderDTO orderDto = orderService.getById(orderId);
        log.info("Order id = " + orderId + ", action = " + action);
        if (action.equals(JspValue.APPROVE)) {
            orderService.updateOrderApproval(true, orderId);
            bookService.decrementQuantityBook(orderDto.getBookDto().getId());
        } else if (action.equals(JspValue.CANCEL)) {
            orderService.deleteById(orderDto.getId());
            bookService.incrementQuantityBook(orderDto.getBookDto().getId());
            return new CommandInfo(AdminPath.ALL_ORDERS.getPath(), RoutingType.REDIRECT);
        }
        return new CommandInfo(AdminPath.ALL_ORDERS.getPath(), RoutingType.REDIRECT);
    }
}
