package io.github.vitalikulsha.JavaWebProject.filter;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.domain.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.SessionAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

//@WebFilter("/*")
public class AuthFilter implements Filter {
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DaoFactory factory = new DaoFactory();
        userDao = factory.userDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String login = req.getParameter(SessionAttribute.LOGIN);
        String password = req.getParameter(SessionAttribute.PASSWORD);
        HttpSession session = req.getSession();
        if (nonNull(session.getAttribute(SessionAttribute.LOGIN))
                && nonNull(session.getAttribute(SessionAttribute.PASSWORD))) {
            User.Role role = (User.Role) session.getAttribute(SessionAttribute.ROLE);
            moveToPage(req, res, role);
        } else if (userDao.isExist(login, password)) {
            User.Role role = userDao.getByLogin(login).get().getRole();
            req.getSession().setAttribute(SessionAttribute.ROLE, role);
            req.getSession().setAttribute(SessionAttribute.LOGIN, login);
            req.getSession().setAttribute(SessionAttribute.PASSWORD, password);
            moveToPage(req, res, role);
        } else {
            moveToPage(req, res, User.Role.GUEST);
        }
    }

    @Override
    public void destroy() {
    }

    private void moveToPage(final HttpServletRequest req, final HttpServletResponse res, final User.Role role)
            throws ServletException, IOException {
        switch (role) {
            case USER:
//                res.sendRedirect("/reader/book-search");
                req.getRequestDispatcher("/WEB-INF/view/book-search.jsp").forward(req, res);
            case ADMIN:
//                res.sendRedirect("/login");
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
            default:
//                res.sendRedirect("/login");
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }
}
