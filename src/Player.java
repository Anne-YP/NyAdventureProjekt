import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        inventory = new ArrayList<>();
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
}

