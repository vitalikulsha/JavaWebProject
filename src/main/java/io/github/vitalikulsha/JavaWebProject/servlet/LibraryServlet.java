package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = {"/admin", "/admin/book-info", "/admin/reader-info", "/admin/order-info",
        "/admin/all-orders", "/admin/all-readers", "/admin/all-books",
        "/reader", "/reader/reader-orders", "/reader/book-search", "/reader/catalog", "/reader/order",
        "/reader/reader-order-info", "/reader/edit",
        "/logout", "/login", "/register", "/locale"})
public class LibraryServlet extends HttpServlet {
    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        commandFactory = CommandFactory.instance();
        log.debug("Servlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Method doGet() starting");
        log.info("Request method: " + request.getMethod());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Method doPost() starting");
        log.info("Request method: " + request.getMethod());
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("processRequest() starting");
        String servletPath = request.getServletPath();
        log.info("ServletPath: " + servletPath);
        Command command = commandFactory.getCommand(servletPath);
        CommandInfo commandInfo = command.execute(request, response);
        String resource = commandInfo.getResource();
        log.info("Resource: " + resource);
        switch (commandInfo.getRoutingType()) {
            case FORWARD:
                log.info("Routing type is FORWARD");
                getServletContext().getRequestDispatcher(resource).forward(request, response);
                break;
            case REDIRECT:
                log.info("Routing type is REDIRECT");
                response.sendRedirect(request.getContextPath() + resource);
                break;
            default:
                getServletContext().getRequestDispatcher(Page.ERROR_404).forward(request, response);
        }
    }
}
