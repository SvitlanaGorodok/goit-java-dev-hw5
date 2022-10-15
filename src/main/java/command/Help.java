package command;

import command.order.CreateOrder;
import command.order.DeleteOrder;
import command.order.GetInventory;
import command.order.GetOrderById;
import command.pet.*;
import command.user.*;

public class Help implements Command{
    public static final String COMMAND_NAME = "help";
    Console console;

    public Help(Console console) {
        this.console = console;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        System.out.printf("To see list of commands please enter %s\n", Help.COMMAND_NAME);
        System.out.println("-----------------------ORDERS-----------------------");
        System.out.printf("To create order please enter %s\n", CreateOrder.COMMAND_NAME);
        System.out.printf("To get order by id please enter %s\n", GetOrderById.COMMAND_NAME);
        System.out.printf("To get order inventory please enter %s\n", GetInventory.COMMAND_NAME);
        System.out.printf("To delete order please enter %s\n", DeleteOrder.COMMAND_NAME);
        System.out.println("-------------------------PETS-------------------------");
        System.out.printf("To create pet please enter %s\n", CreatePet.COMMAND_NAME);
        System.out.printf("To update pet please enter %s\n", UpdatePet.COMMAND_NAME);
        System.out.printf("To update pet with form please enter %s\n", UpdatePetFrom.COMMAND_NAME);
        System.out.printf("To get pet by id please enter %s\n", GetPetById.COMMAND_NAME);
        System.out.printf("To get pets by status please enter %s\n", GetPetByStatus.COMMAND_NAME);
        System.out.printf("To upload pet image please enter %s\n", UploadPetImage.COMMAND_NAME);
        System.out.printf("To delete pet please enter %s\n", DeletePet.COMMAND_NAME);
        System.out.println("-------------------------USERS-------------------------");
        System.out.printf("To create user please enter %s\n", CreateUser.COMMAND_NAME);
        System.out.printf("To create users from list please enter %s\n", CreateUsersFromList.COMMAND_NAME);
        System.out.printf("To create users from array please enter %s\n", CreateUsersFromArray.COMMAND_NAME);
        System.out.printf("To update user please enter %s\n", UpdateUser.COMMAND_NAME);
        System.out.printf("To get user by name please enter %s\n", GetUserByName.COMMAND_NAME);
        System.out.printf("To login user please enter %s\n", UserLogin.COMMAND_NAME);
        System.out.printf("To logout user please enter %s\n", UserLogout.COMMAND_NAME);
        System.out.printf("To delete user please enter %s\n", DeleteUser.COMMAND_NAME);
        System.out.println("-------------------------------------------------------");
    }
}
