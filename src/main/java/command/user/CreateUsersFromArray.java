package command.user;

import command.Command;
import command.Console;
import models.User;
import service.UserService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CreateUsersFromArray implements Command {
    public static final String COMMAND_NAME = "create_users_from_array";
    Console console;
    UserService userService;
    URI uri;

    public CreateUsersFromArray(Console console, UserService userService, URI uri) {
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
        User[] users;
        System.out.println("Please enter amount of users");
        users = new User[Integer.parseInt(console.read())];
        for (int i=0; i<users.length; i++){
            users[i] = userService.readUser(console);
        }
        System.out.println(userService.postUsersFromArray(uri, users));
    }
}
