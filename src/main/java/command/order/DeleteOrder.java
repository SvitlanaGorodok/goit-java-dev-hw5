package command.order;

import command.Command;
import command.Console;
import service.OrderService;

import java.io.IOException;
import java.net.URI;

public class DeleteOrder implements Command {
    public static final String COMMAND_NAME = "delete_order";
    Console console;
    OrderService orderService;
    URI uri;

    public DeleteOrder(Console console, OrderService orderService, URI uri) {
        this.console = console;
        this.orderService = orderService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        long id;
        System.out.println("Please enter order id:");
        while (true){
            try{
                id = Long.parseLong(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        orderService.deleteOrder(uri, id);
    }
}
