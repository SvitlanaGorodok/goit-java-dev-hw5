package command.user;

import command.Command;
import command.Console;
import service.UserService;

import java.io.IOException;
import java.net.URI;

public class UserLogin implements Command {
    public static final String COMMAND_NAME = "user_login";
    Console console;
    UserService userService;
    URI uri;

    public UserLogin(Console console, UserService userService, URI uri) {
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
        String login;
        String password;
        System.out.println("Please enter username:");
        login = console.read();
        System.out.println("Please enter password:");
        password = console.read();
        System.out.println(userService.userLogin(uri, login, password));
    }
}
