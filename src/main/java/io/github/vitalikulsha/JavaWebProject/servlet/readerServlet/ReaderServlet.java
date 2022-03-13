package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.UserPages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = {"/reader", "/reader/reader-orders"})
public class ReaderServlet extends HttpServlet {
    private final static int ITEM_PER_PAGE = 5;
    private Pagination<OrderDto> pagination;
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        pagination = new Pagination<>(ITEM_PER_PAGE);
        log.debug("ReaderServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("ReaderServlet doGet() starting");
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        if (servletPath.equals(UserPages.READER_ORDERS.getPage())) {
            User user = (User) session.getAttribute(Attribute.USER);
            String page = request.getParameter(Parameter.PAGE);
            OrderService orderService = factory.orderService();
            List<OrderDto> orders = orderService.getOrdersByUserId(user.getId());
            int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
            List<Integer> pages = pagination.getPageNumbers(orders);
            orders = pagination.getItemsPerPage(orders, pageNumber);
            String url = session.getServletContext().getContextPath() + request.getServletPath() + "?";
            request.setAttribute(Attribute.URL, url);
            request.setAttribute(Attribute.PAGES, pages);
            request.setAttribute(Attribute.USER_ORDERS, orders);
            getServletContext().getRequestDispatcher("/WEB-INF/view/reader/reader-orders.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/view/reader/reader.jsp").forward(request, response);
        }
    }
}
