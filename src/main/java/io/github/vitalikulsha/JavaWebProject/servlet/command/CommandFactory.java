package io.github.vitalikulsha.JavaWebProject.servlet.command;

import io.github.vitalikulsha.JavaWebProject.servlet.command.impl.*;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory instance = new CommandFactory();

    private final static Map<String, Command> commands = new HashMap<>() {{
        //adminPath
        put(AdminPath.ADMIN.getPath(), new AdminCommand());
        put(AdminPath.ALL_BOOKS.getPath(), new AllBooksCommand());
        put(AdminPath.ALL_ORDERS.getPath(), new AllOrdersCommand());
        put(AdminPath.ALL_READERS.getPath(), new AllReadersCommand());
        put(AdminPath.BOOK_INFO.getPath(), new BookInfoCommand());
        put(AdminPath.ORDER_INFO.getPath(), new OrderInfoCommand());
        put(AdminPath.READER_INFO.getPath(), new ReaderInfoCommand());
        //userPath adn generalPath
        put(UserPath.BOOK_SEARCH.getPath(), new BookSearchCommand());
        put(UserPath.CATALOG.getPath(), new CatalogCommand());
        put(UserPath.EDIT.getPath(), new EditReaderCommand());
        put(UserPath.LOCALE.getPath(), new LocaleCommand());
        put(UserPath.LOGOUT.getPath(), new LogoutCommand());
        put(UserPath.LOGIN.getPath(), new LoginCommand());
        put(UserPath.ORDER.getPath(), new OrderCommand());
        put(UserPath.READER.getPath(), new ReaderCommand());
        put(UserPath.READER_ORDERS.getPath(), new ReaderOrdersCommand());
        put(UserPath.READER_ORDER_INFO.getPath(), new ReaderOrderInfoCommand());
        put(UserPath.REGISTER.getPath(), new RegisterCommand());
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
