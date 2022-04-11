package io.github.vitalikulsha.javawebproject.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet command interface.
 */
public interface Command {
    /**
     * Method is called by servlet when processing request.
     * Gets an object of class CommandInfo
     *
     * @param request  servlet request
     * @param response servlet response
     * @return the command info, that contains page path and routing type
     */
    CommandInfo execute(HttpServletRequest request, HttpServletResponse response);
}
