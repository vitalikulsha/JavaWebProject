package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookSearchCommand implements Command {
    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandInfo(Page.BOOK_SEARCH, RoutingType.FORWARD);
    }
}
