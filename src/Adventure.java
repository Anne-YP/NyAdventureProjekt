import java.util.Locale;
import java.util.Scanner;
public class Adventure {
    private Room currentRoom;

    public Adventure() {
        Room r1 = new Room("You are in room 1",
                "A room with no distinct features, except two doors.");

        Room r2 = new Room("You are in room 2",
                "Water drips from the ceiling somewhere in the dark.");

        Room r3 = new Room("You are in room 3",
                "There is a tree growing in a ray of light. A sword is hanging from one of its branches.");

        Room r4 = new Room("You are in room 4",
                "There is a orc standing in front of you. What do you do?");

        Room r6 = new Room("you are in room 6",
                "There is a bridge crossing a black lake.")

        // Room 1 setters
        r1.setEast(r2);

        // Room 2 setters
        r2.setWest(r1);

        currentRoom = r1;
    }

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

                    default:
                        System.out.println("Unknown command");
                }
            }
        }
    }
