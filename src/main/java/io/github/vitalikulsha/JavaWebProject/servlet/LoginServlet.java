package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.AdminPages;
import io.github.vitalikulsha.JavaWebProject.util.page.UserPages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = DaoFactory.instance();
        log.debug("LoginServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("LoginServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("LoginServlet doPost() starting");
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        UserDao userDao = factory.userDao();
        if (userDao.isExist(login, password)) {
            User user = userDao.findByLogin(login);
            session.setAttribute(Attribute.USER, user);
            if (user.getRole() == Role.USER) {
                response.sendRedirect(contextPath + UserPages.READER.getPage());
            } else if (user.getRole() == Role.ADMIN) {
                response.sendRedirect(contextPath + AdminPages.ADMIN.getPage());
            }
        } else {
            session.setAttribute("user", null);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}
