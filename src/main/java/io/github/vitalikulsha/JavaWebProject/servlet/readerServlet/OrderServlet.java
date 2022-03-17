package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;;
import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/reader/order")
public class OrderServlet extends HttpServlet {
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("OrderServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("OrderServlet doGet() starting");
        HttpSession session = request.getSession();
        BookService bookService = factory.bookService();
        int bookId = Integer.parseInt(request.getParameter(Parameter.BOOK_ID));
        BookDto bookDto = bookService.getById(bookId);
        session.setAttribute(Attribute.BOOK, bookDto);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("OrderServlet doPost() starting");
        HttpSession session = request.getSession();
        OrderService orderService = factory.orderService();
        BookDto bookDto = (BookDto) session.getAttribute(Attribute.BOOK);
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        ReserveStatus reserveStatus = ReserveStatus.valueOf(request.getParameter(Parameter.RESERVE_STATUS));
        log.info("bookId: " + bookDto.getId() + "; userId: " + user.getId() + "; reserveStatus: " + reserveStatus);
        session.setAttribute(Attribute.USER, user);
        session.setAttribute(Attribute.BOOK, bookDto);
        orderService.createOrder(bookDto.getId(), user.getId(), reserveStatus);
        String contextPath = session.getServletContext().getContextPath();
        response.sendRedirect(contextPath + UserPath.READER_ORDERS.getPath());
    }
}
