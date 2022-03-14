package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.AdminPages;
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
@WebServlet("/admin/all-orders")
public class AllOrdersServlet extends HttpServlet {
    private final static int ITEM_PER_PAGE = 5;
    private Pagination<OrderDto> pagination;
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        pagination = new Pagination<>(ITEM_PER_PAGE);
        log.debug("AllOrdersServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("AllOrdersServlet doGet() starting");
//        HttpSession session = request.getSession();
        OrderService orderService = factory.orderService();
        String page = request.getParameter(Parameter.PAGE);
        String url = request.getServletContext().getContextPath() + AdminPages.ALL_ORDERS.getPage() + "?";
        List<OrderDto> allOrders =  orderService.getAll();
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(allOrders);
        allOrders = pagination.getItemsPerPage(allOrders, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.ALL_ORDERS, allOrders);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/all-orders.jsp").forward(request, response);
    }
}
