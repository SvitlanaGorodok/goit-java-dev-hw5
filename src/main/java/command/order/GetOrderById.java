package command.order;

import command.Command;
import command.Console;
import service.OrderService;

import java.io.IOException;
import java.net.URI;

public class GetOrderById implements Command {
    public static final String COMMAND_NAME = "get_order_by_id";
    Console console;
    OrderService orderService;
    URI uri;

    public GetOrderById(Console console, OrderService orderService, URI uri) {
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
        System.out.println(orderService.getOrderById(uri, id));
    }
}
