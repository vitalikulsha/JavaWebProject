package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.util.constant.JspValue;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.servlet.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class OrderCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(JspValue.GET)) {
            try {
                return getCommandInfoGet(request, session);
            } catch (ServiceException e) {
                log.error("Unable to get book by id: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (method.equals(JspValue.POST)) {
            try {
                return getCommandInfoPost(request, session);
            } catch (ServiceException e) {
                log.error("Unable to create new order." + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.ERROR_403, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoGet(HttpServletRequest request, HttpSession session) throws ServiceException {
        BookService bookService = ServiceFactory.instance().bookService();
        int bookId = Integer.parseInt(request.getParameter(RequestParameter.BOOK_ID));
        BookDTO bookDto = bookService.getById(bookId);
        if (bookDto == null || bookDto.getQuantity() == 0) {
            session.setAttribute(SessionAttribute.BOOK_FOUND, false);
            return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
        } else {
            session.setAttribute(SessionAttribute.BOOK_FOUND, true);
            session.setAttribute(SessionAttribute.BOOK, bookDto);
            return new CommandInfo(Page.ORDER, RoutingType.FORWARD);
        }
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request, HttpSession session) throws ServiceException {
        OrderService orderService = ServiceFactory.instance().orderService();
        BookService bookService = ServiceFactory.instance().bookService();
        ReserveStatus reserveStatus = ReserveStatus.valueOf(request.getParameter(RequestParameter.RESERVE_STATUS));
        BookDTO bookDto = (BookDTO) session.getAttribute(SessionAttribute.BOOK);
        UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
        if (!isBookExists(orderService, bookDto, user)) {
            orderService.createOrder(bookDto.getId(), user.getId(), reserveStatus);
            bookService.decrementQuantityBook(bookDto.getId());
        } else {
            log.info("The book id = " + bookDto.getId() + " is already in the list of orders.");
            session.setAttribute(SessionAttribute.BOOK_EXISTS, true);
            return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
        }
        return new CommandInfo(UserPath.READER_ORDERS.getPath(), RoutingType.REDIRECT);
    }

    private boolean isBookExists(OrderService orderService, BookDTO bookDto, UserDTO user) throws ServiceException {
        return orderService.getOrdersByUserId(user.getId())
                .stream()
                .map(o -> o.getBookDto().getId())
                .anyMatch(id -> id == bookDto.getId());
    }
}
