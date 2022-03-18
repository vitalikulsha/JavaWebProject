package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllReadersCommand implements Command {
    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.instance().userService();
        Pagination<UserDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String page = request.getParameter(Parameter.PAGE);
        String url = request.getContextPath() + AdminPath.ALL_READERS.getPath() + "?";
        List<UserDto> allReaders = userService.getUsersByRole(Role.USER);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(allReaders);
        allReaders = pagination.getItemsPerPage(allReaders, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.ALL_READERS, allReaders);
        return new CommandInfo(Page.ALL_READERS, RoutingType.FORWARD);
    }
}
