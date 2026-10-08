import java.util.ArrayList;
public class Room {
    private String name;
    private String description;
    private boolean waterSource;
    private boolean swordTaken;
    private boolean hasSwordTree;
    private boolean hasBreadCreature;
    private boolean breadTaken;
    private boolean mushroomEaten;
    private boolean hasTrapdoor;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private ArrayList<Item> items;

    private ArrayList<Enemy> enemies;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        waterSource = false;
        swordTaken = false;
        hasSwordTree = false;
        hasBreadCreature = false;
        breadTaken = false;
        mushroomEaten = false;

        items = new ArrayList<>();

        enemies = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item findItem(String shortName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        String text = description;

        if (hasSwordTree && !swordTaken) {
            text += " A long sword is hanging from one of its branches.";
        }

        if (hasBreadCreature) {
            if (!breadTaken) {
                text += " A mythical creature is offering you bread.";
            } else {
                text += " A mythical creature is in front of you.";
            }
        }
//        if (hasTrapdoor) {
//            text += "\nA heavy trapdoor is set into the dusty floor.";
//        }
        return text;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Enemy findEnemy(String enemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // Getters for north, south, east and west
    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    // Setters for north, south, east and west
    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    // Getter and Setter for hasWaterSource
    public boolean hasWaterSource() {
        return waterSource;
    }

    public void setWaterSource(boolean waterSource) {
        this.waterSource = waterSource;
    }

    // Getter and Setter for isSwordTaken
    public boolean isSwordTaken() {
        return swordTaken;
    }

    public void setSwordTaken(boolean swordTaken) {
        this.swordTaken = swordTaken;
    }

    // Getter and Setter for hasSwordTree
    public boolean hasSwordTree() {
        return hasSwordTree;
    }

    public void setSwordTree(boolean hasSwordTree) {
        this.hasSwordTree = hasSwordTree;
    }

    //Getter and Setter for hasBreadCreature
    public boolean hasBreadCreature() {
        return hasBreadCreature;
    }

    public void setBreadCreature(boolean hasBreadCreature) {
        this.hasBreadCreature = hasBreadCreature;
    }

    //Getter and Setter for isBreadTaken
    public boolean isBreadTaken() {
        return breadTaken;
    }

    public void setBreadTaken(boolean breadTaken) {
        this.breadTaken = breadTaken;
    }

    //Getter and Setter for mushroomEaten
    public boolean isMushroomEaten() {
        return mushroomEaten;
    }

    public void setMushroomEaten(boolean mushroomEaten) {
        this.mushroomEaten = mushroomEaten;
    }

    //Getter and Setter for hasTrapdoor
    public boolean hasTrapdoor() {
        return hasTrapdoor;
    }

    public void setTrapdoor(boolean hasTrapdoor) {
        this.hasTrapdoor = hasTrapdoor;
    }
}
