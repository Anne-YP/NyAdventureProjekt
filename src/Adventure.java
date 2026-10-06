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

            if (command.startsWith("take ")) {
                String itemName = command.substring(5);

                ui.showMessage("Searching for: [" + itemName + "]");
                Item item = player.getCurrentRoom().findItem(itemName);

                if (item != null) {
                    player.getCurrentRoom().removeItem(item);
                    player.addItem(item);
                    if (item.getShortName().equals("sword")) {
                        player.getCurrentRoom().setSwordTaken(true);
                    }
                    if (item.getShortName().equals("bread") && player.getCurrentRoom().hasBreadCreature()) {
                        player.getCurrentRoom().setBreadTaken(true);
                    }


                    ui.showMessage("You picked up " + item.getLongName());
                } else {
                    ui.showMessage("There is nothing like " + itemName + " to take around here.");
                }
                continue;

            }
            if (command.startsWith("drop ")) {
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

            if (command.equals("drink water")) {
                Item item = player.findItem("container");

                if (item == null) {
                    ui.showMessage("You need something to collect the water in");

                    continue;
                }
                Container container = (Container) item;
                if (!container.isFilled()) {
                    ui.showMessage("Your container is empty.");
                    continue;
                }

                Liquid liquid = container.getContent();
                player.changeHealth(liquid.getHealthPoints());

                ui.showMessage("You drank the " + liquid.getLongName());
                container.empty();
                continue;
            }

            if (command.startsWith("eat ") || command.startsWith("drink ")) {
                String itemName;

                if (command.startsWith("eat ")) {
                    itemName = command.substring(4);
                } else {
                    itemName = command.substring(6);
                }
                EatOutcome outcome = player.eat(itemName);

                switch (outcome.getResult()) {
                    case NOT_FOUND -> ui.showMessage("There is nothing like " +
                            itemName + " to eat or drink around here.");

                    case NOT_EDIBLE -> ui.showMessage("You can't eat or drink " + outcome.getItemName());

                    case SUCCESS -> ui.showMessage("You " + outcome.getVerb() +
                            " " + outcome.getItemName());
                }
                continue;
            }

            if (command.equals("fill container")) {
                Item item = player.findItem("container");

                if (item == null) {
                    ui.showMessage("You don't have a container");
                    continue;
                }

                if (!player.getCurrentRoom().hasWaterSource()) {
                    ui.showMessage("There is no water source here.");
                    continue;
                }
                // Adventure skal have at vide, at container er fra Container-klassen
                // og at det skal behandle container som et Container-objekt. Derfor følgende:
                Container container = (Container) item;

                if (container.isFilled()) {
                    ui.showMessage("The container is already full.");
                    continue;
                }

                container.fill(map.getWater());
                ui.showMessage("You have filled the container with water.");
                continue;
            }

            if(command.startsWith("equip ")) {
                String itemName = command.substring(6);

                EquipOutcome outcome = player.equip(itemName);

                switch (outcome.getResult()) {
                    case NOT_FOUND -> ui.showMessage("You don't have a " + itemName);

                    case NOT_A_WEAPON -> ui.showMessage(outcome.getItemName() + " is not a weapon");

                    case SUCCESS -> ui.showMessage("You have equipped " + outcome.getItemName());
                }
                continue;
            }

                if(command.equals("attack")) {
                    AttackOutcome result = player.attack();

                    switch (result.getResult()) {
                        case NO_WEAPON_EQUIPPED -> ui.showMessage("You have no weapon equipped");

                        case WEAPON_EMPTY -> ui.showMessage("Your " + result.getWeapon().getShortName()
                         + " is empty");

                        case SUCCESS -> ui.showMessage("You " + result.getWeapon().getAttackVerb() +
                                " " + result.getWeapon().getShortName() + " at the empty air." +
                                result.getWeapon().getUsesLeftText());
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
                        } else {
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
                                "Type in 'look' to look around the room.\n" +
                                "To move type either 'go north', 'go south', 'go east' or 'go west'.\n" +
                                "To pick up an item type 'take *name of item*'.\n" +
                                "To drop an item type 'drop *name of item*'.\n" +
                                "To see your health type 'health'.\n" +
                                "To exit game type 'exit'");
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
                        if (player.getInventory().isEmpty()) {
                            ui.showMessage("Your inventory is empty.");
                        } else {
                            ui.showMessage("You are carrying:");

                            for (Item item : player.getInventory()) {
                                ui.showMessage("- " + item.getLongName());
                            }
                        }
                        Weapon equippedWeapon = player.getEquippedWeapon();
                        if(equippedWeapon != null) {
                            ui.showMessage("Equipped: " + equippedWeapon.getLongName());
                        }
                        break;

                    case "health":
                        ui.showMessage("Health: " + player.getHealth() +
                                " - " + player.getHealthdescription());
                        break;



                    default:
                        ui.showMessage("Unknown command");


                }
            }
        }
    }