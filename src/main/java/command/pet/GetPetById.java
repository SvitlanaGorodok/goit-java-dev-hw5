package command.pet;

import command.Command;
import command.Console;
import service.PetService;

import java.io.IOException;
import java.net.URI;

public class GetPetById implements Command {
    public static final String COMMAND_NAME = "get_pet_by_id";
    Console console;
    PetService petService;
    URI uri;

    public GetPetById(Console console, PetService petService, URI uri) {
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
        System.out.println(petService.getPetById(uri, petId));
    }
}
