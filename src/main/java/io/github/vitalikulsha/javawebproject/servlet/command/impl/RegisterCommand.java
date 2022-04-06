package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.util.service.validator.ValidationPattern;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.Value;
import io.github.vitalikulsha.javawebproject.util.path.UserPath;
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
        HttpSession session = request.getSession();
        String url = request.getServletPath();
        log.info("current URL: " + url);
        session.setAttribute(SessionAttribute.URL, url);
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
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        long phoneNumber = Long.parseLong(request.getParameter(RequestParameter.PHONE_NUMBER));
        String email = request.getParameter(RequestParameter.EMAIL);
        log.info("login: " + login + "; password: " + password + ", firstName: " + firstName
                + ", lastName: " + lastName + ", phoneNumber: " + phoneNumber);
        if (userService.getByLogin(login) != null) {
            return getCommandInfoByLogin(request, login);
        } else if (userService.getByEmail(email) != null) {
            return getCommandInfoByEmail(request, email);
        }
        if (!userService.createUser(login, password, firstName, lastName, phoneNumber, email)) {
            return getCommandInfoByUser(request, login, password, firstName, lastName, phoneNumber, email);
        }
        session.setAttribute(SessionAttribute.USER, userService.getByLogin(login));
        log.info("New user: " + userService.getByLogin(login));
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }

    private CommandInfo getCommandInfoByUser(HttpServletRequest request, String login, String password, String firstName, String lastName, long phoneNumber, String email) {
        log.info("Unable to update user data.");
        List<String> invalidFields = getInvalidFields(login, password, firstName, lastName, phoneNumber, email);
        request.setAttribute(SessionAttribute.INVALID_FIELD, invalidFields);
        return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoByEmail(HttpServletRequest request, String email) {
        log.info("User with email " + email + " already exists");
        request.setAttribute(SessionAttribute.USER_EXISTS, true);
        request.setAttribute(RequestParameter.EMAIL, email);
        return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoByLogin(HttpServletRequest request, String login) {
        log.info("User with login " + login + " already exists");
        request.setAttribute(SessionAttribute.USER_EXISTS, true);
        request.setAttribute(RequestParameter.LOGIN, login);
        return new CommandInfo(Page.REGISTER, RoutingType.FORWARD);
    }

    private List<String> getInvalidFields(String login, String password, String firstName, String lastName,
                                          long phoneNumber, String email) {
        List<String> invalidFields = new ArrayList<>();
        if (!login.matches(ValidationPattern.LOGIN_PATTERN)) {
            invalidFields.add(RequestParameter.LOGIN);
        }
        if (!password.matches(ValidationPattern.PASSWORD_PATTERN)) {
            invalidFields.add(RequestParameter.PASSWORD);
        }
        if (!firstName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(RequestParameter.FIRST_NAME);
        }
        if (!lastName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(RequestParameter.LAST_NAME);
        }
        if (!String.valueOf(phoneNumber).matches(ValidationPattern.PHONE_PATTERN)) {
            invalidFields.add(RequestParameter.PHONE_NUMBER);
        }
        if (!email.matches(ValidationPattern.EMAIL_PATTERN)) {
            invalidFields.add(RequestParameter.EMAIL);
        }
        return invalidFields;
    }
}
