package command.user;

import command.Command;
import command.Console;
import models.User;
import service.UserService;

import java.io.IOException;
import java.net.URI;

public class UpdateUser implements Command {
    public static final String COMMAND_NAME = "update_user";
    Console console;
    UserService userService;
    URI uri;

    public UpdateUser(Console console, UserService userService, URI uri) {
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
        System.out.println(userService.putUser(uri, userService.readUser(console)));
    }
}
