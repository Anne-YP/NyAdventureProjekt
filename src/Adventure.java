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
    //
    public void play() {

        player.getCurrentRoom();

        boolean isPlaying = true;
        while (isPlaying) {

            ui.showPrompt();
            String command = ui.getCommand();
            switch (command) {
                case "look":
                    ui.showMessage(player.getCurrentRoom().getName());
                    ui.showMessage(player.getCurrentRoom().getDescription());
                    break;

                case "exit":
                    isPlaying = false;
                    ui.showMessage("Goodbye!");
                    break;

                case "help":
                    ui.showMessage("Help!");
                    break;


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

                default:
                    ui.showMessage("Unknown command");


            }
        }
    }
}

