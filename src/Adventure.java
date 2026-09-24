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

        Room r5 = new Room("You are in room 5",
                "THIS IS A SPECIAL ROOM");

        Room r6 = new Room("you are in room 6",
                "There is a bridge crossing a black lake.");

        Room r7 = new Room("You are in room 7",
                "There is an empty water container lying on the floor.");

        Room r8 = new Room("You are in room 8",
                "There is a mythical creature offering you food.");

        Room r9 = new Room("You are in room 9",
                "A rusty key is kept in a glass jar. Do you want to collect it?");


        //Room 1 setters
        r1.setEast(r2);
        r1.setSouth(r4);

        //Room 2 setters
        r2.setWest(r1);
        r2.setEast(r3);

        //Room 3 setters
        r3.setWest(r2);
        r3.setSouth(r6);

        //Room 4 setters
        r4.setNorth(r1);
        r4.setSouth(r7);

        //Room 5 setters
        r5.setSouth(r8);

        //Room 6 setters
        r6.setNorth(r3);
        r6.setSouth(r9);

        //Room 7 setters
        r7.setNorth(r4);
        r7.setEast(r8);

        //Room 8 setters
        r8.setWest(r7);
        r8.setNorth(r5);
        r8.setEast(r9);

        //Room 9 setters
        r9.setWest(r8);
        r9.setNorth(r6);

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
