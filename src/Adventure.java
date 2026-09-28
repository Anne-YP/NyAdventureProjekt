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
    public void play() {
        Scanner scanner = new Scanner(System.in);

        player.getCurrentRoom()

            boolean isPlaying = true;
            while (isPlaying) {

                System.out.print("> ");
                String command = scanner.nextLine().toLowerCase();
                switch (command) {
                    case "look":
                        System.out.println(player.getCurrentRoom().getName());
                        System.out.println(player.getCurrentRoom().getDescription());
                        break;

                    case "exit":
                        isPlaying = false;
                        System.out.println("Goodbye!");
                        break;

                    case "help":
                        System.out.println("Help!");
                        break;


                    case "go north":
                        if (player.move("north")) {
                            System.out.println(player.getCurrentRoom().getName());
                            System.out.println(player.getCurrentRoom().getDescription());
                        } else {
                            System.out.println("You can't go that way!");
                        }
                        break;

                    case "go south":
                        if (player.move("south")) {
                            System.out.println(player.getCurrentRoom().getName());
                            System.out.println(player.getCurrentRoom().getDescription());
                        } else {
                            System.out.println("You can't go that way!");
                        }
                        break;

                    case "go east":
                        if (player.move("(east")) {
                            System.out.println(player.getCurrentRoom().getName());
                            System.out.println(player.getCurrentRoom().getDescription());
                        } else {
                            System.out.println("You can't go that way!");
                        }
                        break;
                    case "go west":
                        if (player.move("west")) {
                            System.out.println(player.getCurrentRoom().getName());
                            System.out.println(player.getCurrentRoom().getDescription());
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


