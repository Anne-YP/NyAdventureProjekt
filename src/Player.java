import java.util.ArrayList;

public class Player {
    // Skal have current room fordi det er spilleren, der "ved" hvor de er henne og ikke Map der ved, hvor spilleren er.

    private Room currentRoom;

    private ArrayList<Item> inventory;


    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }
    public void removeItem(Item item) {
        inventory.remove(item);
    }

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

}

