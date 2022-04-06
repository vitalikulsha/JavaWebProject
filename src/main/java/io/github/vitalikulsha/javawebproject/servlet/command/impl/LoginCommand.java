package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.JspValue;
import io.github.vitalikulsha.javawebproject.util.path.AdminPath;
import io.github.vitalikulsha.javawebproject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, request.getServletPath());
        if (method.equals(JspValue.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable compare user by login and password: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.LOGIN, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = ServiceFactory.instance().userService();
        if (userService.isExists(login, password)) {
            UserDTO userDto = userService.getByLogin(login);
            session.setAttribute(SessionAttribute.USER, userDto);
            if (userDto.getRole() == Role.READER) {
                return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
            } else if (userDto.getRole() == Role.ADMIN) {
                return new CommandInfo((AdminPath.ADMIN.getPath()), RoutingType.REDIRECT);
            }
        }
        request.setAttribute(SessionAttribute.USER_FOUND, false);
        return new CommandInfo(Page.LOGIN, RoutingType.FORWARD);
    }
}
