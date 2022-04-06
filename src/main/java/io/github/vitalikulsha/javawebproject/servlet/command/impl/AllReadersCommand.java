package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class AllReadersCommand implements Command {
    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.instance().userService();
        Pagination<UserDTO> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String url = request.getContextPath() + AdminPath.ALL_READERS.getPath() + "?";
        try {
            List<UserDTO> allReaders = userService.getUsersByRole(Role.READER);
            request.setAttribute(SessionAttribute.URL, url);
            pagination.paginate(allReaders, request, SessionAttribute.ALL_READERS);
            return new CommandInfo(Page.ALL_READERS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get users by role: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
