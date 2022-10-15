package command;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read(){return scanner.nextLine();}
    public void run(List<Command> commands) throws IOException, InterruptedException {
        while (true) {
            String input = scanner.nextLine();
            boolean isInputCorrect = false;
            for (Command command : commands) {
                if (command.canExecute(input)) {
                    command.execute();
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                System.out.println("Command not found. Please enter help to see all commands");
            }
        }
    }
}
