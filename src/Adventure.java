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

                if (itemName.equals("key") && !player.getCurrentRoom().isMushroomEaten()) {
                    ui.showMessage("You must eat the mushroom before you can reach the key.");
                    continue;
                }
                if (itemName.equalsIgnoreCase("book")) {
                    Enemy orc = player.getCurrentRoom().findEnemy("orc");
                    if (orc != null) {
                        ui.showMessage("The orc is guarding the book.");
                        continue;
                    }
                }

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
                    continue;
                } else {
                    if (itemName.equals("water") && player.getCurrentRoom().hasWaterSource()) {
                        ui.showMessage("You need something to collect the water in first.");
                        continue;
                    }
                    ui.showMessage("There is nothing like " + itemName + " to take around here.");
                    continue;
                }
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

                    case SUCCESS -> {
                        ui.showMessage("You " + outcome.getVerb() +
                                " " + outcome.getItemName());

                        if (itemName.equalsIgnoreCase("mushroom")) {
                            ui.showMessage("The mushroom tastes terrible and makes you feel sick.\n" +
                                    "A strange sensation spreads through your body.\n " +
                                    "You instantly regret eating the mushroom.");
                        }
                    }
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

                Container container = (Container) item;

                if (container.isFilled()) {
                    ui.showMessage("The container is already full.");
                    continue;
                }

                container.fill(map.getWater());
                ui.showMessage("You have filled the container with water.");
                continue;
            }

            if (command.startsWith("equip ")) {
                String itemName = command.substring(6);

                EquipOutcome outcome = player.equip(itemName);

                switch (outcome.getResult()) {
                    case NOT_FOUND -> ui.showMessage("You don't have a " + itemName);

                    case NOT_A_WEAPON -> ui.showMessage(outcome.getItemName() + " is not a weapon");

                    case SUCCESS -> ui.showMessage("You have equipped " + outcome.getItemName());
                }
                continue;
            }

            if (command.equals("attack")) {
                AttackOutcome result = player.attack();

                switch (result.getResult()) {
                    case NO_WEAPON_EQUIPPED -> ui.showMessage("You have no weapon equipped");

                    case WEAPON_EMPTY -> ui.showMessage("Your " + result.getWeapon().getShortName()
                            + " is empty");

                    case SUCCESS -> ui.showMessage("You " + result.getWeapon().getAttackVerb() +
                            " the " + result.getWeapon().getShortName() + " at the empty air." +
                            result.getWeapon().getUsesLeftText());
                }
                continue;
            }

            if (command.startsWith("attack ")) {
                String enemyName = command.substring(7);

                Enemy enemy = player.getCurrentRoom().findEnemy(enemyName);
                if (enemy == null) {
                    ui.showMessage("Enemy not found.");
                    continue;
                }

                AttackOutcome result = player.attack();
                switch (result.getResult()) {
                    case NO_WEAPON_EQUIPPED -> ui.showMessage("You have no weapon equipped.");

                    case WEAPON_EMPTY -> ui.showMessage("You ran out of arrows and your " +
                            result.getWeapon().getShortName() + " is empty.");

                    case SUCCESS -> {
                        boolean enemyDied = enemy.hit(player.getEquippedWeapon());

                        if (result.getWeapon()instanceof RangedWeapon) {
                            ui.showMessage("You " + result.getWeapon().getAttackVerb() +
                                    " an arrow at the " + enemy.getShortName() + ".");
                            // Bortset fra dragen som spits fire
                        }
                        else {
                            ui.showMessage("You " + result.getWeapon().getAttackVerb() + " the " +
                                    result.getWeapon().getAttackNoun() + " at the " + enemy.getShortName() + ".");
                        }
                        ui.showMessage(result.getWeapon().getUsesLeftText());

                        if (enemyDied) {
                            ui.showMessage("The enemy has been slain!");
                            ui.showMessage(enemy.getWeapon().getLongName() + " drops to the ground.");
                        }
                        else {
                            boolean enemyAttacked = enemy.attack(player);
                            ui.showMessage("The " + enemy.getShortName() + " strikes back.");
                            if (!enemyAttacked) {
                                ui.showMessage(enemy.getLongName() + " could not attack.");
                            }
                            if (player.getHealth() <= 0) {
                                ui.showMessage("You have died.");
                                ui.showMessage("GAME OVER!");

                                isPlaying = false;

                                continue;
                            }
                            ui.showMessage("Enemy health: " + enemy.getHealth());
                            ui.showMessage("Player health: " + player.getHealth());
                        }
                    }
                }
                continue;
            }

            if (command.equalsIgnoreCase("open trapdoor")) {
                if (!player.getCurrentRoom().hasTrapdoor()) {
                    ui.showMessage("There is no trapdoor here.");
                    continue;
                }
                if (player.findItem("key") == null) {
                    ui.showMessage("You tried to open the trapdoor but it is locked.");
                continue;
            }
                Enemy dragon = player.getCurrentRoom().findEnemy("dragon");
                if (dragon != null) {
                    ui.showMessage("The dragon blocks your way.");
                    continue;
                }
            ui.showMessage("You unlocked the trapdoor and escape!");

                player.setCurrentRoom(map.getWinningRoom());
                ui.showMessage(player.getCurrentRoom().getName());
                ui.showMessage(player.getCurrentRoom().getDescription());

                isPlaying = false;

                continue;
        }

                switch (command) {
                    case "look":
                        Room currentRoom = player.getCurrentRoom();
                        ui.showMessage(currentRoom.getName());
                        ui.showMessage(currentRoom.getDescription());

                        ArrayList<Item> items = currentRoom.getItems();

                        if (player.getCurrentRoom().getItems().isEmpty()) {
                            ui.showMessage("");
                            ui.showMessage("There are no items here.");
                        } else {
                            ui.showMessage("");
                            ui.showMessage("Items in this room:");
                            for (Item item : player.getCurrentRoom().getItems()) {
                                ui.showMessage("- " + item.getLongName());
                            }
                        }

                        ArrayList<Enemy> enemies = currentRoom.getEnemies();

                        ui.showMessage("");
                        ui.showMessage("Enemies in this room: ");

                        if(enemies.isEmpty()) {
                            ui.showMessage("- none");
                        }
                        else{
                            for(Enemy enemy: enemies) {
                                ui.showMessage("- " + enemy.getLongName());
                                ui.showMessage(enemy.getDescription());
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
                                "To see your inventory type 'inventory'.\n" +
                                "To equip your weapon type 'equip *name of weapon*'.\n" +
                                "To attack enemies type 'attack *name of enemy*'.\n" +
                                "To exit game type 'exit'");
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