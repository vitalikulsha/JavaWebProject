package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = DaoFactory.instance();
        log.debug("RegisterServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        String userName = request.getParameter(Parameter.USERNAME);
        long phoneNumber = Long.parseLong(request.getParameter(Parameter.PHONE_NUMBER));
        String email = request.getParameter(Parameter.EMAIL);
        UserDao userDao = factory.userDao();
        if (userDao.findByLogin(login) != null) {
            //придумать отрибут для session, который бы фиксировал существующий login
            getServletContext().getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            return;
        }
//        int id = userDao.maxId() + 1;
//        User user = new User(id, login, password, userName, phoneNumber, email, Role.USER);
//        session.setAttribute(Attribute.USER, user);


        response.sendRedirect("/library/reader");
    }
}
