import java.util.Scanner;

public class UserInterface {
    // Class for communication between player and game
// Commands start, exit, look, help and inputs

    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getCommand(){
        return scanner.nextLine().toLowerCase();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}


/*
private String translateDirection(String command) {
    return switch (command) {
    case "go north", "n", "north" --> "go north"
    case "go south", "s", "south" --> "go south"
    case "go east", "e", "east" --> "go east"
    case "go west", "w", "west" --> "go west
 */