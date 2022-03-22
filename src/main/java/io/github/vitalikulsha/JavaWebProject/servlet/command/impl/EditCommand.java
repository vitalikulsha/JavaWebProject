package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
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
public class EditCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(Value.POST)) {
            return getCommandInfoPost(request);
        }
        return new CommandInfo(Page.EDIT, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(Attribute.USER);
        UserService userService = ServiceFactory.instance().userService();
        String userName = request.getParameter(Parameter.USERNAME);
        long phoneNumber = Long.parseLong(request.getParameter(Parameter.PHONE_NUMBER));
        String email = request.getParameter(Parameter.EMAIL);
        log.info("userName: " + userName + ", phoneNumber: " + phoneNumber + ", email: " + email);
        UserDto newUserDto = userService.updateUser(userName, phoneNumber, email, userDto.getId());
        session.setAttribute(Attribute.USER, newUserDto);
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }
}
