package io.github.vitalikulsha.javawebproject.servlet;

import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Library servlet.
 */
@Slf4j
@WebServlet(urlPatterns = {"/admin", "/admin/book-info", "/admin/reader-info", "/admin/order-info",
        "/admin/all-orders", "/admin/all-readers", "/admin/all-books",
        "/reader", "/reader/reader-orders", "/reader/book-search", "/reader/catalog", "/reader/order",
        "/reader/reader-order-info", "/reader/edit",
        "/logout", "/login", "/register", "/locale", "/error-403", "/error-404", "/error-500"})
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

    /**
     * Executes a request based on the servlet path and type method (GET or POST)
     * and forwards request or sends redirect.
     *
     * @param request  HTTP request
     * @param response HTTP response
     * @throws IOException      is thrown when forward exception occurs
     * @throws ServletException is thrown when redirect exception occurs
     */
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
                request.getRequestDispatcher(resource).forward(request, response);
                break;
            case REDIRECT:
                log.info("Routing type is REDIRECT");
                response.sendRedirect(request.getContextPath() + resource);
                break;
            default:
                request.getRequestDispatcher(Page.ERROR_404).forward(request, response);
        }
    }
}
