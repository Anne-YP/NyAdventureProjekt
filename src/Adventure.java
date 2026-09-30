import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Adventure {
    private Player player;
    private Map map;
    private UserInterface ui;

    public Adventure() {
        map = new Map();
        player = new Player(map.getStartingRoom());
        ui = new UserInterface();
    }

    public void play() {

        player.getCurrentRoom();

        boolean isPlaying = true;
        while (isPlaying) {

            ui.showPrompt();
            String command = ui.getCommand();

            if(command.startsWith("take ")) {
                String itemName = command.substring(5);

                ui.showMessage("Searching for: [" + itemName + "]");
                Item item = player.getCurrentRoom().findItem(itemName);

                if(item != null) {
                    player.getCurrentRoom().removeItem(item);
                    player.addItem(item);

                    ui.showMessage("You picked up " + item.getLongName());
                }
                else {
                    ui.showMessage("There is nothing like " +itemName + " to take around here.");
                }
                continue;

            }
            if(command.startsWith("drop ")) {
                String itemName = command.substring(5);

                Item item = player.findItem(itemName);

                if (item != null) {
                    player.removeItem(item);
                    player.getCurrentRoom().addItem(item);

                    ui.showMessage("You dropped " + item.getLongName());
                } else {
                    ui.showMessage("You don't have anything like " + itemName + " in your inventory.");
                }
                continue;
            }

            switch (command) {

                case "look":
                    Room currentRoom = player.getCurrentRoom();
                    ui.showMessage(currentRoom.getName());
                    ui.showMessage(currentRoom.getDescription());

                    ArrayList<Item> items = currentRoom.getItems();

                    if (player.getCurrentRoom().getItems().isEmpty()) {
                    ui.showMessage("There are no items here.");
                    }
                    else {
                    ui.showMessage("Items in this room:");
                    for (Item item : player.getCurrentRoom().getItems()) {
                    ui.showMessage("- " + item.getLongName());
                    }
                        }
                    break;

                case "exit":
                    isPlaying = false;
                    ui.showMessage("Goodbye!");
                    break;

                case "help":
                    ui.showMessage("You are trapped in a labyrinth and you need to find your way out.\n" +
                            "Type in 'look' to look around the room\n" +
                            "To move type either 'go north', 'go south', 'go east' or 'go west'\n" +
                            "To pick up an item type 'take *name of item*'\n" +
                            "To drop an item type 'drop *name of item*'");
                    break;

                // Check refactor possibility of .getCurrentRoom() in move-command
                case "north":
                    if (player.move("north")) {
                        ui.showMessage(player.getCurrentRoom().getName());
                        ui.showMessage(player.getCurrentRoom().getDescription());
                    } else {
                        ui.showMessage("You can't go that way!");
                    }
                    break;

                case "south":
                    if (player.move("south")) {
                        ui.showMessage(player.getCurrentRoom().getName());
                        ui.showMessage(player.getCurrentRoom().getDescription());
                    } else {
                        ui.showMessage("You can't go that way!");
                    }
                    break;

                case "east":
                    if (player.move("east")) {
                        ui.showMessage(player.getCurrentRoom().getName());
                        ui.showMessage(player.getCurrentRoom().getDescription());
                    } else {
                        ui.showMessage("You can't go that way!");
                    }
                    break;
                case "west":
                    if (player.move("west")) {
                        ui.showMessage(player.getCurrentRoom().getName());
                        ui.showMessage(player.getCurrentRoom().getDescription());
                    } else {
                        ui.showMessage("You can't go that way!");
                    }
                    break;

                case "inventory":
                    if(player.getInventory().isEmpty()) {
                        ui.showMessage("Your inventory is empty.");
                    }
                    else {
                        ui.showMessage("You are carrying:");

                        for(Item item: player.getInventory()) {
                            ui.showMessage("- " + item.getLongName());
                        }
                    }
                    break;

                default:
                    ui.showMessage("Unknown command");


            }
        }
    }
}

