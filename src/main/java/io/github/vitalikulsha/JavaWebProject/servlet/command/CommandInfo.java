package io.github.vitalikulsha.JavaWebProject.servlet.command;

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

    public void setResource(String resource) {
        this.resource = resource;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }

    public void setRoutingType(RoutingType routingType) {
        this.routingType = routingType;
    }
}
