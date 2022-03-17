package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = {"/admin", "/admin/book-info", "/admin/reader-info", "/admin/order-info",
        "/admin/all-orders", "/admin/all-readers"})
public class AdminServlet extends HttpServlet {
    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        commandFactory = CommandFactory.instance();
        log.debug("AdminServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("AdminServlet doGet() starting");
        String servletPath = request.getServletPath();
        log.info("servletPath: " + servletPath);
        Command command = commandFactory.getCommand(servletPath);
        CommandInfo commandInfo = command.execute(request, response);
        String resource = commandInfo.getResource();
        log.info("Resource path: " + resource);
        switch (commandInfo.getRoutingType()) {
            case FORWARD:
                getServletContext().getRequestDispatcher(resource).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + resource);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
