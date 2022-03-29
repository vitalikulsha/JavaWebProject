package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LocaleCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute(Attribute.URL);
        String locale = request.getParameter(Parameter.LOCALE);
        log.info("url: " + url + ", locale: " + locale);
        session.setAttribute(Attribute.LOCALE, locale);
        return new CommandInfo(url, RoutingType.REDIRECT);
    }
}
