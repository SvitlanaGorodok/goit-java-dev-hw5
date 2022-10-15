import command.Command;
import command.Console;
import command.Exit;
import command.Help;
import command.order.CreateOrder;
import command.order.DeleteOrder;
import command.order.GetInventory;
import command.order.GetOrderById;
import command.pet.*;
import command.user.*;
import service.OrderService;
import service.PetService;
import service.UserService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ORDERS = "https://petstore.swagger.io/v2/store/order";
    private static final String PETS = "https://petstore.swagger.io/v2/pet";
    private static final String USERS = "https://petstore.swagger.io/v2/user";
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        OrderService orderService = new OrderService();
        PetService petService = new PetService();
        UserService userService = new UserService();
        System.out.printf("To see list of commands please enter %s\n", Help.COMMAND_NAME);
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(console));
        commands.add(new Exit(console));
        commands.add(new CreateOrder(console, orderService, URI.create(ORDERS)));
        commands.add(new GetOrderById(console, orderService, URI.create(ORDERS)));
        commands.add(new DeleteOrder(console, orderService, URI.create(ORDERS)));
        commands.add(new GetInventory(console, orderService));
        commands.add(new CreatePet(console, petService, URI.create(PETS)));
        commands.add(new UpdatePet(console, petService, URI.create(PETS)));
        commands.add(new DeletePet(console, petService, URI.create(PETS)));
        commands.add(new UpdatePetFrom(console, petService, URI.create(PETS)));
        commands.add(new GetPetById(console, petService, URI.create(PETS)));
        commands.add(new GetPetByStatus(console, petService, URI.create(PETS)));
        commands.add(new UploadPetImage(console, petService, URI.create(PETS)));
        commands.add(new CreateUser(console, userService, URI.create(USERS)));
        commands.add(new CreateUsersFromList(console, userService, URI.create(USERS)));
        commands.add(new CreateUsersFromArray(console, userService, URI.create(USERS)));
        commands.add(new UpdateUser(console, userService, URI.create(USERS)));
        commands.add(new DeleteUser(console, userService, URI.create(USERS)));
        commands.add(new GetUserByName(console, userService, URI.create(USERS)));
        commands.add(new UserLogin(console, userService, URI.create(USERS)));
        commands.add(new UserLogout(console, userService, URI.create(USERS)));
        console.run(commands);
        scanner.close();
    }
}
