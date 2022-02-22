package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.domain.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.SessionAttribute;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = new DaoFactory();
        log.debug("LoginServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("LoginServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("LoginServlet doPost() starting");
        HttpSession session = request.getSession();
        String login = request.getParameter(SessionAttribute.LOGIN);
        String password = request.getParameter(SessionAttribute.PASSWORD);
        UserDao userDao = factory.userDao();
        Optional<User> userOptional = userDao.getByLogin(login);
        if(userOptional.isEmpty()){
            session.setAttribute("notFound", "user");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            return;
        }
        User user = userOptional.get();
        if (user.getPassword().equals(password)) {
            session.setAttribute(SessionAttribute.USER, user);
            if (user.getRole() == User.Role.USER) {
                response.sendRedirect("/library/reader/book-search");
            } else if (user.getRole() == User.Role.ADMIN) {
                response.sendRedirect("/WEB-INF/view/admin.jsp");
            }
        } else {
            session.setAttribute("notFound", "password");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}
