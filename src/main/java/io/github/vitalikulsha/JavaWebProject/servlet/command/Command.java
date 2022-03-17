package io.github.vitalikulsha.JavaWebProject.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandInfo execute(HttpServletRequest request, HttpServletResponse response);
}
