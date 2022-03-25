package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.service.validator.ValidationPattern;
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
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class RegisterCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(Value.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable to register new user: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.instance().userService();
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        String firstName = request.getParameter(Parameter.FIRST_NAME);
        String lastName = request.getParameter(Parameter.LAST_NAME);
        long phoneNumber = Long.parseLong(request.getParameter(Parameter.PHONE_NUMBER));
        String email = request.getParameter(Parameter.EMAIL);
        log.info("login: " + login + "; password: " + password + ", firstName: " + firstName
                + ", lastName: " + lastName + ", phoneNumber: " + phoneNumber);
        if (userService.getByLogin(login) != null) {
            log.info("User with login " + login + " already exists");
            request.setAttribute(Attribute.USER_EXISTS, true);
            request.setAttribute(Parameter.LOGIN, login);
            return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
        } else if (userService.getByEmail(email) != null) {
            log.info("User with email " + email + " already exists");
            request.setAttribute(Attribute.USER_EXISTS, true);
            request.setAttribute(Parameter.EMAIL, email);
            return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
        }
        if (!userService.createUser(login, password, firstName, lastName, phoneNumber, email)) {
            log.info("Failed to update user data.");
            List<String> invalidFields = getInvalidFields(login, password, firstName, lastName, phoneNumber, email);
            request.setAttribute(Attribute.INVALID_FIELD, invalidFields);
            return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
        }
        session.setAttribute(Attribute.USER, userService.getByLogin(login));
        log.info("New user: " + userService.getByLogin(login));
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }

    private List<String> getInvalidFields(String login, String password, String firstName, String lastName,
                                          long phoneNumber, String email) {
        List<String> invalidFields = new ArrayList<>();
        if (!login.matches(ValidationPattern.LOGIN_PATTERN)) {
            invalidFields.add(Parameter.LOGIN);
        }
        if (!password.matches(ValidationPattern.PASSWORD_PATTERN)) {
            invalidFields.add(Parameter.PASSWORD);
        }
        if (!firstName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(Parameter.FIRST_NAME);
        }
        if (!lastName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(Parameter.LAST_NAME);
        }
        if (!String.valueOf(phoneNumber).matches(ValidationPattern.PHONE_PATTERN)) {
            invalidFields.add(Parameter.PHONE_NUMBER);
        }
        if (!email.matches(ValidationPattern.EMAIL_PATTERN)) {
            invalidFields.add(Parameter.EMAIL);
        }
        return invalidFields;
    }
}
