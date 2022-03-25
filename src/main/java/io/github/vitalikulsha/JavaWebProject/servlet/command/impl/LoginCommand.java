package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(Value.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable compare user by login and password.", e);
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.LOGIN, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        UserService userService = ServiceFactory.instance().userService();
        if (userService.isExists(login, password)) {
            UserDto userDto = userService.getByLogin(login);
            session.setAttribute(Attribute.USER, userDto);
            if (userDto.getRole() == Role.READER) {
                return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
            } else if (userDto.getRole() == Role.ADMIN) {
                return new CommandInfo((AdminPath.ADMIN.getPath()), RoutingType.REDIRECT);
            }
        }
        request.setAttribute(Attribute.USER_FOUND, false);
        return new CommandInfo(Page.LOGIN, RoutingType.FORWARD);
    }
}
