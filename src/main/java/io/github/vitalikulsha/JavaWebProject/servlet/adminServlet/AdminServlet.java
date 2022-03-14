package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.AdminPages;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = {"/admin", "/admin/book-info", "/admin/reader-info", "/admin/order-info"})
public class AdminServlet extends HttpServlet {
    private final static int ITEM_PER_PAGE = 5;
    private Pagination<OrderDto> pagination;
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        pagination = new Pagination<>(ITEM_PER_PAGE);
        log.debug("AdminServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("AdminServlet doGet() starting");
        String servletPath = request.getServletPath();
        if (servletPath.equals(AdminPages.BOOK_INFO.getPage())) {
            showBookInfo(request, response);
        } else if (servletPath.equals(AdminPages.READER_INFO.getPage())) {
            showReaderInfo(request, response);
        } else if (servletPath.equals(AdminPages.ORDER_INFO.getPage())) {
            showOrderInfo(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
        }
    }

    private void showOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = factory.orderService();
        int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
        OrderDto orderDto = orderService.getById(orderId);
        request.setAttribute(Attribute.ORDER, orderDto);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/order-info.jsp").forward(request, response);
    }

    private void showReaderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = factory.userService();
        OrderService orderService = factory.orderService();
        int readerId = Integer.parseInt(request.getParameter(Parameter.READER_ID));
        String page = request.getParameter(Parameter.PAGE);
        String url = request.getServletContext().getContextPath()
                + AdminPages.READER_INFO.getPage()
                + "?" + Parameter.READER_ID + "=" + readerId;
        UserDto reader = userService.getById(readerId);
        List<OrderDto> readerOrders = orderService.getOrdersByUserId(reader.getId());
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(readerOrders);
        readerOrders = pagination.getItemsPerPage(readerOrders, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.READER_ORDERS, readerOrders);
        request.setAttribute(Attribute.READER, reader);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/reader-info.jsp").forward(request, response);
    }

    private void showBookInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = factory.bookService();
        int bookId = Integer.parseInt(request.getParameter(Parameter.BOOK_ID));
        BookDto bookDto = bookService.getById(bookId);
        request.setAttribute(Attribute.BOOK, bookDto);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/book-info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
