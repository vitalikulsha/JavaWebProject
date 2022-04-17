package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.servlet.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, AdminPath.ADMIN.getPath());
        log.info("Admin URL: " + request.getServletPath());
        log.info("Admin locale: " + session.getAttribute(SessionAttribute.LOCALE));
        return new CommandInfo(Page.ADMIN, RoutingType.FORWARD);
    }
}