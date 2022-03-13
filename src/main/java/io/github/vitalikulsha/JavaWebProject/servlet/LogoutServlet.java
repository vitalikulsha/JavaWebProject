package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.page.UserPages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

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
        String contextPath = session.getServletContext().getContextPath();
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }
        response.sendRedirect(contextPath + UserPages.LOGIN.getPage());
    }

}
