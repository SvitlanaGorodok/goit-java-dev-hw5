package command.order;

import command.Command;
import command.Console;
import service.OrderService;

import java.io.IOException;
import java.net.URI;

public class GetInventory implements Command {
    public static final String COMMAND_NAME = "get_order_inventory";
    Console console;
    OrderService orderService;

    public GetInventory(Console console, OrderService orderService) {
        this.console = console;
        this.orderService = orderService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        orderService.getOrderInventory();
    }
}
