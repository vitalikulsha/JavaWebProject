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

    /**
     * Command map for specific servlet paths.
     */
    private static final Map<String, Command> commands = new HashMap<>();

    /*
      Command map initialization.
     */
    static {
        //admin path
        commands.put(AdminPath.ADMIN.getPath(), new AdminCommand());
        commands.put(AdminPath.ALL_BOOKS.getPath(), new AllBooksCommand());
        commands.put(AdminPath.ALL_ORDERS.getPath(), new AllOrdersCommand());
        commands.put(AdminPath.ALL_READERS.getPath(), new AllReadersCommand());
        commands.put(AdminPath.BOOK_INFO.getPath(), new BookInfoCommand());
        commands.put(AdminPath.ORDER_INFO.getPath(), new OrderInfoCommand());
        commands.put(AdminPath.READER_INFO.getPath(), new ReaderInfoCommand());
        //user path
        commands.put(UserPath.BOOK_SEARCH.getPath(), new BookSearchCommand());
        commands.put(UserPath.CATALOG.getPath(), new CatalogCommand());
        commands.put(UserPath.EDIT.getPath(), new EditReaderCommand());
        commands.put(UserPath.ORDER.getPath(), new OrderCommand());
        commands.put(UserPath.READER.getPath(), new ReaderCommand());
        commands.put(UserPath.READER_ORDERS.getPath(), new ReaderOrdersCommand());
        commands.put(UserPath.READER_ORDER_INFO.getPath(), new ReaderOrderInfoCommand());
        //general path
        commands.put(GuestPath.ERROR_403.getPath(), new Error403Command());
        commands.put(GuestPath.ERROR_404.getPath(), new Error404Command());
        commands.put(GuestPath.ERROR_500.getPath(), new Error500Command());
        commands.put(GuestPath.LOGIN.getPath(), new LoginCommand());
        commands.put(UserPath.LOGOUT.getPath(), new LogoutCommand());
        commands.put(GuestPath.LOCALE.getPath(), new LocaleCommand());
        commands.put(GuestPath.REGISTER.getPath(), new RegisterCommand());
    }

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
