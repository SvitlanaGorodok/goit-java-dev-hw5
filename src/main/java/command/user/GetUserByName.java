package command.user;

import command.Command;
import command.Console;
import service.UserService;

import java.io.IOException;
import java.net.URI;

public class GetUserByName implements Command {
    public static final String COMMAND_NAME = "get_user_by_name";
    Console console;
    UserService userService;
    URI uri;

    public GetUserByName(Console console, UserService userService, URI uri) {
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
        System.out.println("Please enter user name:");
        System.out.println(userService.getUserByName(uri, console.read()));
    }
}
