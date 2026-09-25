public class UserInterface {
}
// Class for communication between player and game
// Commands start, exit, look, help and inputs
// Fix currentRoom issues
/*
public void play () {
    Scanner scanner = new Scanner(System.in);

    boolean isPlaying = true;
    while (isPlaying) {

     System.out.print("> ");
        String command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "look":
                System.out.println(currentRoom.getName());
                System.out.println(currentRoom.getDescription());
                break;

            case "exit":
                isPlaying = false;
                System.out.println("Goodbye!");
                break;

            case "help":
                System.out.println("Help!");
                break;


            case "go north":
                if (currentRoom.getNorth() != null) {
                    currentRoom = currentRoom.getNorth();

                    System.out.println(currentRoom.getName());
                    System.out.println(currentRoom.getDescription());
                } else {
                    System.out.println("You can't go that way!");
                }
                break;

            case "go south":
                if (currentRoom.getSouth() != null) {
                    currentRoom = currentRoom.getSouth();

                    System.out.println(currentRoom.getName());
                    System.out.println(currentRoom.getDescription());
                } else {
                    System.out.println("You can't go that way!");
                }
                break;

            case "go east":
                if (currentRoom.getEast() != null) {
                    currentRoom = currentRoom.getEast();

                    System.out.println(currentRoom.getName());
                    System.out.println(currentRoom.getDescription());
                } else {
                    System.out.println("You can't go that way!");
                }
                break;
            case "go west":
                if (currentRoom.getWest() != null) {
                    currentRoom = currentRoom.getWest();

                    System.out.println(currentRoom.getName());
                    System.out.println(currentRoom.getDescription());
                } else {
                    System.out.println("You can't go that way!");
                }
                break;

            default:
                System.out.println("Unknown command");
        }
    }
}
 */