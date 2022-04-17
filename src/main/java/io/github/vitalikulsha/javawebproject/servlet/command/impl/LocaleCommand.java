package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LocaleCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute(SessionAttribute.URL);
        String locale = request.getParameter(RequestParameter.LOCALE);
        log.info("Locale url: " + url + ", locale: " + locale);
        session.setAttribute(SessionAttribute.LOCALE, locale);
        return new CommandInfo(url, RoutingType.REDIRECT);
    }
}
