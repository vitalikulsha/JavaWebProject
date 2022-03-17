package io.github.vitalikulsha.JavaWebProject.servlet.command;

import io.github.vitalikulsha.JavaWebProject.servlet.command.impl.*;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory instance = new CommandFactory();

    private final static Map<String, Command> commands = new HashMap<>() {{
        put(AdminPath.ORDER_INFO.getPath(), new OrderInfoCommand());
        put(AdminPath.BOOK_INFO.getPath(), new BookInfoCommand());
        put(AdminPath.READER_INFO.getPath(), new ReaderInfoCommand());
        put(AdminPath.ADMIN.getPath(), new AdminCommand());
        put(AdminPath.ALL_ORDERS.getPath(), new AllOrdersCommand());
        put(AdminPath.ALL_READERS.getPath(), new AllReadersCommand());
    }};

    public CommandFactory() {
    }

    public static CommandFactory instance() {
        return instance;
    }

    public Command getCommand(String path) {
        return commands.get(path);
    }
}
