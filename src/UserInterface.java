import java.util.Scanner;

public class UserInterface {
    // Class for communication between player and game
// Commands start, exit, look, help and inputs

    private Scanner scanner;
    private Adventure adventure;


    public void showPrompt() {
        System.out.print("> ");
    }

    public UserInterface() {
        scanner = new Scanner(System.in);
        adventure = new Adventure();

    }

    public String getCommand() {
        String command = scanner.nextLine().trim().toLowerCase();

        return translateDirection(command);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
//

    private String translateDirection(String command) {
        return switch (command) {
            case "go north", "n", "north" -> "north";
            case "go south", "s", "south" -> "south";
            case "go east", "e", "east" -> "east";
            case "go west", "w", "west" -> "west";
            default -> command;
        };
    }
}