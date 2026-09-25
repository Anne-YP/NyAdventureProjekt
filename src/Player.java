public class Player {
    // Skal have current room fordi det er spilleren, der "ved" hvor de er henne og ikke Map der ved, hvor spilleren er.

    private Room currentRoom;

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

    void main() {
    }

}

