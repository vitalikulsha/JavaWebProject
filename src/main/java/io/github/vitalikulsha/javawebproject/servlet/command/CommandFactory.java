package io.github.vitalikulsha.javawebproject.servlet.command;

import io.github.vitalikulsha.javawebproject.servlet.command.impl.*;
import io.github.vitalikulsha.javawebproject.servlet.path.AdminPath;
import io.github.vitalikulsha.javawebproject.servlet.path.GuestPath;
import io.github.vitalikulsha.javawebproject.servlet.path.UserPath;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides command with given servlet path.
 */
public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private static final Map<String, Command> commands = new HashMap<>() {{
        //admin path
        put(AdminPath.ADMIN.getPath(), new AdminCommand());
        put(AdminPath.ALL_BOOKS.getPath(), new AllBooksCommand());
        put(AdminPath.ALL_ORDERS.getPath(), new AllOrdersCommand());
        put(AdminPath.ALL_READERS.getPath(), new AllReadersCommand());
        put(AdminPath.BOOK_INFO.getPath(), new BookInfoCommand());
        put(AdminPath.ORDER_INFO.getPath(), new OrderInfoCommand());
        put(AdminPath.READER_INFO.getPath(), new ReaderInfoCommand());
        //user path
        put(UserPath.BOOK_SEARCH.getPath(), new BookSearchCommand());
        put(UserPath.CATALOG.getPath(), new CatalogCommand());
        put(UserPath.EDIT.getPath(), new EditReaderCommand());
        put(UserPath.ORDER.getPath(), new OrderCommand());
        put(UserPath.READER.getPath(), new ReaderCommand());
        put(UserPath.READER_ORDERS.getPath(), new ReaderOrdersCommand());
        put(UserPath.READER_ORDER_INFO.getPath(), new ReaderOrderInfoCommand());
        //general path
        put(GuestPath.ERROR_403.getPath(), new Error403Command());
        put(GuestPath.ERROR_404.getPath(), new Error404Command());
        put(GuestPath.ERROR_500.getPath(), new Error500Command());
        put(GuestPath.LOGIN.getPath(), new LoginCommand());
        put(UserPath.LOGOUT.getPath(), new LogoutCommand());
        put(GuestPath.LOCALE.getPath(), new LocaleCommand());
        put(GuestPath.REGISTER.getPath(), new RegisterCommand());
    }};

    private CommandFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance of CommandFactory
     */
    public static CommandFactory instance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param path servlet path
     * @return the command to the corresponding servlet path
     */
    public Command getCommand(String path) {
        return commands.get(path);
    }
}
