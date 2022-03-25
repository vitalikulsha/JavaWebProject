package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class AllReadersCommand implements Command {
    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.instance().userService();
        Pagination<UserDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String url = request.getContextPath() + AdminPath.ALL_READERS.getPath() + "?";
        try {
            List<UserDto> allReaders = userService.getUsersByRole(Role.READER);
            request.setAttribute(Attribute.URL, url);
            pagination.paginate(allReaders, request, Attribute.ALL_READERS);
            return new CommandInfo(Page.ALL_READERS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get users by role.", e);
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
