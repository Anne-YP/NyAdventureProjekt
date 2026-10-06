public class Map {

    private Room startingRoom;
    private Liquid water;

    public Map() {
        // Create rooms
        Room r1 = new Room("You are in room 1",
                "A room with no distinct features, except two doors.");

        Room r2 = new Room("You are in room 2",
                "Water drips from the ceiling somewhere in the dark.");

        Room r3 = new Room("You are in room 3",
                "There is a tree growing in a ray of light.");

        Room r4 = new Room("You are in room 4",
                "An orc standing in front of you. Kill the orc to get the book.");

        Room r5 = new Room("You are in room 5",
                "A dragon is guarding a treasure chest filled with gold.\n" +
                        "Defeat the dragon to collect the gold and win the game!");

        Room r6 = new Room("you are in room 6",
                "A bridge is crossing a black lake.");

        Room r7 = new Room("You are in room 7",
                "An empty water container is lying on the floor.");

        Room r8 = new Room("You are in room 8",
                "A rusty key is kept inside a pale glowing mushroom.\n" +
                        "Eat the mushroom to access the key.");

        Room r9 = new Room("You are in room 9",
                "A mythical creature is offering you bread.");

        // Create items
        Item key = new Item("key", "a rusty key inside a pale glowing mushroom");
        // Item sword = new Item("sword", "a long sword");
        Item lamp = new Item("lamp", "an ancient oil lamp");
        // Item archbow = new Item("archbow", "a golden bejewelled archbow with arrow");
        Item gold = new Item("gold", "a treasure chest filled with gold");
        Item book = new Item("book", "a book: 'How to defeat a dragon'");

        // Create container
        Container container = new Container("container", "a water container");

        // Create Food Items
        Food bread = new Food("bread",
                "a loaf of stale bread", 20);

        Food mushroom = new Food("mushroom",
                "a pale glowing mushroom", -50);

        // Create Liquid water
        water = new Liquid("water", "fresh cold water", 15);

        // Create weapons
        Weapon sword = new MeleeWeapon("sword", "a long sword", 20);
        Weapon archbow = new RangedWeapon("archbow", "a golden bejewelled archbow " +
                "with arrows", 10, 5);

        // Assign items and food items to rooms + Water Source(true/false) + Sword Tree (true/false)
        r2.addItem(lamp);
        r2.setWaterSource(true);

        r3.addItem(sword);
        r3.setSwordTree(true);

        r4.addItem(book);

        r5.addItem(gold);

        r6.addItem(archbow);
        r6.addItem(water);

        r7.addItem(container);

        r8.addItem(key);
        r8.addItem(mushroom);

      //  r9.addItem(food);
        r9.addItem(bread);

        // set directions for rooms
        r1.setEast(r2);
        r1.setSouth(r4);

        r2.setWest(r1);
        r2.setEast(r3);

        r3.setWest(r2);
        r3.setSouth(r6);

        r4.setNorth(r1);
        r4.setSouth(r7);

        r5.setSouth(r8);

        r6.setNorth(r3);
        r6.setSouth(r9);

        r7.setNorth(r4);
        r7.setEast(r8);

        r8.setWest(r7);
        r8.setNorth(r5);
        r8.setEast(r9);

        r9.setWest(r8);
        r9.setNorth(r6);

        startingRoom = r1;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }
    // getter til water
    public Liquid getWater(){
        return water;
    }
}