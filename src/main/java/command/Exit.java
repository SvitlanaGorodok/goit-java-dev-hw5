package command;

public class Exit implements Command{
    public static final String COMMAND_NAME = "exit";
    private static final String BYE_MESSAGE = "Bye!";
    Console console;

    public Exit(Console console) {
        this.console = console;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        System.out.println(BYE_MESSAGE);
        System.exit(0);
    }
}
