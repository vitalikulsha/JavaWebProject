package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.AdminPages;
import lombok.extern.slf4j.Slf4j;

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
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("AdminServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("AdminServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        UserService userService = factory.userService();
        OrderService orderService = factory.orderService();
        BookService bookService = factory.bookService();
        if(servletPath.equals(AdminPages.BOOK_INFO.getPage())){
            int bookId = Integer.parseInt(request.getParameter(Parameter.BOOK_ID));
            BookDto bookDto = bookService.getById(bookId);
            request.setAttribute(Attribute.BOOK, bookDto);
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/book-info.jsp").forward(request, response);
        } else if(servletPath.equals(AdminPages.READER_INFO.getPage())){
            int readerId = Integer.parseInt(request.getParameter(Parameter.READER_ID));
            User reader = userService.getById(readerId);
            List<OrderDto> readerOrders = orderService.getOrdersByUserId(reader.getId());
            request.setAttribute(Attribute.READER_ORDERS, readerOrders);
            request.setAttribute(Attribute.READER, reader);
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/reader-info.jsp").forward(request, response);
        } else if(servletPath.equals(AdminPages.ORDER_INFO.getPage())){
            int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
            OrderDto orderDto = orderService.getById(orderId);
            request.setAttribute(Attribute.ORDER, orderDto);
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/order-info.jsp").forward(request, response);
        }
    }
}
