package command.user;

import command.Command;
import command.Console;
import service.UserService;

import java.io.IOException;
import java.net.URI;

public class UserLogout implements Command {
    public static final String COMMAND_NAME = "user_logout";
    Console console;
    UserService userService;
    URI uri;

    public UserLogout(Console console, UserService userService, URI uri) {
        this.console = console;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        System.out.println(userService.userLogout(uri));
    }
}
