package command.pet;

import command.Command;
import command.Console;
import service.PetService;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

public class UploadPetImage implements Command {
    public static final String COMMAND_NAME = "upload_pet_image" +
            "";
    Console console;
    PetService petService;
    URI uri;

    public UploadPetImage(Console console, PetService petService, URI uri) {
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
        Path path;
        System.out.println("Please enter pet id:");
        while (true){
            try{
                petId = Long.parseLong(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter image path (example: Dog.jpg):");
        path = new File(console.read()).toPath();
        System.out.println(petService.uploadPetImage(uri, petId, path));
    }
}
