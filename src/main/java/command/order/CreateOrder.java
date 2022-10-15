package command.order;

import command.Command;
import command.Console;
import models.Order;
import models.OrderStatus;
import service.OrderService;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;

public class CreateOrder implements Command {
    public static final String COMMAND_NAME = "create_order";
    Console console;
    OrderService orderService;
    URI uri;

    public CreateOrder(Console console, OrderService orderService, URI uri) {
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
        Order order = new Order();
        System.out.println("Please enter order id:");
        while (true){
            try{
                order.setId(Long.parseLong(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter pet id:");
        while (true){
            try{
                order.setPetId(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter quantity:");
        while (true){
            try{
                order.setQuantity(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter ship date:");
        while (true){
            try{
                order.setShipDate(String.valueOf(Date.valueOf(console.read())));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Invalid value. Please use format YYYY-MM-DD!");
            }
        }
        System.out.println("Please enter order status (choose from options 'placed', 'approved', 'delivered'):");
        while (true){
                String status = console.read();
                if (status.equals(OrderStatus.PLACED.getName())){
                    order.setStatus(OrderStatus.PLACED.getName());
                    break;
                } else if (status.equals(OrderStatus.APPROVED.getName())){
                    order.setStatus(OrderStatus.PLACED.getName());
                    break;
                } else if (status.equals(OrderStatus.DELIVERED.getName())){
                    order.setStatus(OrderStatus.DELIVERED.getName());
                    break;
                } else {
                System.out.println("Invalid status! Please choose from options 'placed', 'approved', 'delivered'.");
            }
        }
        System.out.println("Does order complete? Please enter Y/N:");
        while (true) {
            String complete = console.read();
            if (complete.equals("Y")) {
                order.setComplete(true);
                break;
            } else if (complete.equals("N")) {
                order.setComplete(false);
                break;
            } else {
                System.out.println("Incorrect value. Please enter Y/N:");
            }
        }
        System.out.println(orderService.postOrder(uri, order));
    }
}
