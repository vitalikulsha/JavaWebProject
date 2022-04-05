package io.github.vitalikulsha.javawebproject.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Info of {@link Command#execute(HttpServletRequest, HttpServletResponse)} containing page path and routing type.
 */
public class CommandInfo {
    private String resource;
    private RoutingType routingType;

    public CommandInfo(String resource, RoutingType routingType) {
        this.resource = resource;
        this.routingType = routingType;
    }

    public String getResource() {
        return resource;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }
}
