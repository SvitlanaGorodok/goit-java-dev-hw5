package command.user;

import command.Command;
import command.Console;
import models.User;
import service.UserService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CreateUsersFromList implements Command {
    public static final String COMMAND_NAME = "create_users_from_list";
    Console console;
    UserService userService;
    URI uri;

    public CreateUsersFromList(Console console, UserService userService, URI uri) {
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
        List<User> users = new ArrayList<>();
        users.add(userService.readUser(console));
        while (true) {
            System.out.println("Do you want to add more users? Please enter Y/N:");
            String answer = console.read();
            if (answer.equals("Y")) {
                users.add(userService.readUser(console));
            } else if (answer.equals("N")) {
                break;
            } else {
                System.out.println("Incorrect value. Please enter Y/N!");
            }
        }
        System.out.println(userService.postUsersFromList(uri, users));
    }
}
