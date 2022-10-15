package command.pet;

import command.Command;
import command.Console;
import models.Category;
import models.Pet;
import models.PetStatus;
import models.Tag;
import service.PetService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DeletePet implements Command {
    public static final String COMMAND_NAME = "delete_pet";
    Console console;
    PetService petService;
    URI uri;

    public DeletePet(Console console, PetService petService, URI uri) {
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
        System.out.println("Please enter pet id:");
        while (true){
            try{
                petId = Long.parseLong(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        petService.deletePet(uri, petId);
    }
}
