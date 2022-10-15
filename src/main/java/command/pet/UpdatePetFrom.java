package command.pet;

import command.Command;
import command.Console;
import models.PetStatus;
import service.PetService;

import java.io.IOException;
import java.net.URI;

public class UpdatePetFrom implements Command {
    public static final String COMMAND_NAME = "update_pet_with_form";
    Console console;
    PetService petService;
    URI uri;

    public UpdatePetFrom(Console console, PetService petService, URI uri) {
        this.console = console;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        long petId;
        String petName;
        String petStatus;
        System.out.println("Please enter pet id:");
        while (true){
            try{
                petId = Long.parseLong(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter pet name:");
        petName = console.read();
        System.out.println("Please enter pet status (choose from options 'available', 'pending', 'sold'):");
        while (true){
            String status = console.read();
            if (status.equals(PetStatus.AVAILABLE.getName())){
                petStatus = PetStatus.AVAILABLE.getName();
                break;
            } else if (status.equals(PetStatus.PENDING.getName())){
                petStatus = PetStatus.PENDING.getName();
                break;
            } else if (status.equals(PetStatus.SOLD.getName())){
                petStatus = PetStatus.SOLD.getName();
                break;
            } else {
                System.out.println("Invalid status! Please choose from options 'available', 'pending', 'sold'.");
            }
        }
        System.out.println(petService.updatePetFrom(uri, petId, petName, petStatus));
    }
}
