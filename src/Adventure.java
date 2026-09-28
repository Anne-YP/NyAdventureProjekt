import java.util.Locale;
import java.util.Scanner;
public class Adventure {
    private Player player;
    private Map map;
    private UserInterface ui;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
        ui = new UserInterface();
    }
}

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
    }

       */
