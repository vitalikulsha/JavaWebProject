package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.debug("LogoutServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("LogoutServlet doGet() starting");
        HttpSession session = request.getSession();
        session.removeAttribute(Attribute.USER);
        response.sendRedirect("/library/login");
        log.info("Session invalidated successfully.");
        log.info("User logged out successfully.");
    }

}
