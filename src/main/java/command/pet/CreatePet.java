package command.pet;

import command.Command;
import command.Console;
import models.*;
import service.PetService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CreatePet implements Command {
    public static final String COMMAND_NAME = "create_pet";
    Console console;
    PetService petService;
    URI uri;

    public CreatePet(Console console, PetService petService, URI uri) {
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
        Pet pet = new Pet();
        Category category = new Category();
        List<String> photoUrls = new ArrayList<>();
        Long tagId;
        List<Tag> tags = new ArrayList<>();
        System.out.println("Please enter pet id:");
        while (true){
            try{
                pet.setId(Long.parseLong(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter category id:");
        while (true){
            try{
                category.setId(Long.parseLong(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter category name:");
        category.setName(console.read());
        pet.setCategory(category);
        System.out.println("Please enter pet name:");
        pet.setName(console.read());
        System.out.println("Please enter photo url:");
        photoUrls.add(console.read());
        while (true) {
            System.out.println("Do you want to add more photo urls? Please enter Y/N:");
            String answer = console.read();
            if (answer.equals("Y")) {
                System.out.println("Please enter photo url:");
                photoUrls.add(console.read());
            } else if (answer.equals("N")) {
                break;
            } else {
                System.out.println("Incorrect value. Please enter Y/N!");
            }
        }
        pet.setPhotoUrls(photoUrls);
        System.out.println("Please enter tag id:");
        while (true){
            try{
                tagId = Long.parseLong(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter tag name:");
        tags.add(new Tag(tagId, console.read()));
        while (true) {
            System.out.println("Do you want to add more tags? Please enter Y/N:");
            String answer = console.read();
            if (answer.equals("Y")) {
                System.out.println("Please enter tag id:");
                while (true){
                    try{
                        tagId = Long.parseLong(console.read());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Invalid value. Use digits!");
                    }
                }
                System.out.println("Please enter tag name:");
                tags.add(new Tag(tagId, console.read()));
            } else if (answer.equals("N")) {
                break;
            } else {
                System.out.println("Incorrect value. Please enter Y/N!");
            }
        }
        pet.setTags(tags);
        System.out.println("Please enter pet status (choose from options 'available', 'pending', 'sold'):");
        while (true){
            String status = console.read();
            if (status.equals(PetStatus.AVAILABLE.getName())){
                pet.setStatus(PetStatus.AVAILABLE.getName());
                break;
            } else if (status.equals(PetStatus.PENDING.getName())){
                pet.setStatus(PetStatus.PENDING.getName());
                break;
            } else if (status.equals(PetStatus.SOLD.getName())){
                pet.setStatus(PetStatus.SOLD.getName());
                break;
            } else {
                System.out.println("Invalid status! Please choose from options 'available', 'pending', 'sold'.");
            }
        }
        System.out.println(petService.postPet(uri, pet));
    }
}
