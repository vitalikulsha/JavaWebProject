package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class LogoutCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }
        return new CommandInfo(UserPath.LOGIN.getPath(), RoutingType.REDIRECT);
    }
}
