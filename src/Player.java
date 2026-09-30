import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        inventory = new ArrayList<>();
        health = 100;
    }

// rooms
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean move(String direction) {

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        } else {
            return false;
        }
    }

    // inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Item findItem(String shortName) {
        for(Item item: inventory) {
            if(item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }
    // Health-getter
    public int getHealth() {
        return health;
    }

    public String getHealthdescription() {
        if (health >= 100) {
            return "you are in perfect health";
        }
        if (health >= 50) {
            return "you are in good health, \n" +
                    "but avoid fighting right now";
        }
        if (health >= 25) {
            return "you are wounded - find something healthy to eat";
        }
        if (health >= 1) {
            return "you are barely alive";
        }
        return "you should be dead";
    }
}

