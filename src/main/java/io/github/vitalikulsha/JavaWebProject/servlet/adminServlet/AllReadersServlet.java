package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
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
@WebServlet("/admin/all-readers")
public class AllReadersServlet extends HttpServlet {
    private final static int ITEM_PER_PAGE = 5;
    private Pagination<UserDto> pagination;
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
        HttpSession session = request.getSession();
        UserService userService = factory.userService();
        String page = request.getParameter(Parameter.PAGE);
        String url = session.getServletContext().getContextPath() + AdminPages.ALL_READERS.getPage() + "?";
        List<UserDto> allReaders =  userService.getUsersByRole(Role.USER);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(allReaders);
        allReaders = pagination.getItemsPerPage(allReaders, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.ALL_READERS, allReaders);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/all-readers.jsp").forward(request, response);
    }
}
