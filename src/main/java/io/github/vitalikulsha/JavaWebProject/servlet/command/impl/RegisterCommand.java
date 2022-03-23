package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class RegisterCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(Value.POST)) {
            return getCommandInfoPost(request);
        }
        return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.instance().userService();
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        String firstName = request.getParameter(Parameter.FIRST_NAME);
        String lastName = request.getParameter(Parameter.LAST_NAME);
        long phoneNumber = Long.parseLong(request.getParameter(Parameter.PHONE_NUMBER));
        String email = request.getParameter(Parameter.EMAIL);
        log.info("login: " + login + "; password: " + password
                + ", firstName: " + firstName + ", lastName: " + lastName
                + ", phoneNumber: " + phoneNumber);
        if (userService.getByLogin(login) != null) {
            log.info("User with login " + login + " already exists");
            request.setAttribute(Attribute.IS_EXISTS, true);
            request.setAttribute(Parameter.LOGIN, login);
            return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
        } else if (userService.getByEmail(email) != null) {
            log.info("User with email " + email + " already exists");
            request.setAttribute(Attribute.IS_EXISTS, true);
            request.setAttribute(Parameter.EMAIL, email);
            return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
        }
        if (userService.createUser(login, password, firstName, lastName, phoneNumber, email)) {
            session.setAttribute(Attribute.USER, userService.getByLogin(login));
            log.info("New user: " + userService.getByLogin(login));
        }
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }
}
