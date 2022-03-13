package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
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
@WebServlet("/reader")
public class ReaderServlet extends HttpServlet {
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("ReaderServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("BookSearchServlet doGet() starting");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        OrderService orderService = factory.orderService();
        @SuppressWarnings("unchecked")
        List<OrderDto> orders = (List<OrderDto>) session.getAttribute(Attribute.USER_ORDERS);
        if (orders == null) {
            orders = orderService.getOrderByUserId(user.getId());
        }
        request.setAttribute(Attribute.USER_ORDERS, orders);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/reader.jsp").forward(request, response);
    }
}
