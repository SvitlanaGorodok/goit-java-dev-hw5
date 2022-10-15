package command.user;

import command.Command;
import command.Console;
import models.User;
import service.UserService;

import java.io.IOException;
import java.net.URI;

public class CreateUser implements Command {
    public static final String COMMAND_NAME = "create_user";
    Console console;
    UserService userService;
    URI uri;

    public CreateUser(Console console, UserService userService, URI uri) {
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
        System.out.println(userService.postUser(uri, userService.readUser(console)));
    }
}
