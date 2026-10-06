public class Map {

    private Room startingRoom;
    private Liquid water;

    public Map() {
        // Create rooms
        Room r1 = new Room("You are in room 1",
                "A moldy smell fills the room. Shelves from floor to ceiling are covered" +
                        " in dust. On one shelf lies a bludgeon.");

        Room r2 = new Room("You are in room 2",
                "The moldy smell has intensified, and the sound of waterdrops " +
                        "from the ceiling hitting the floor irritates your ears." +
                        " somewhere in the dark you will find an ancient oil lamp.");

        Room r3 = new Room("You are in room 3",
                "In a ray of light a tree is growing rapidly.");

        Room r4 = new Room("You are in room 4",
                "You are surrounded by a heavy smell of garbage and see" +
                        " an orc standing in front of you." +
                        " Amongst the garbage lies a dusty old book: 'How to defeat a dragon'. " +
                        " Kill the orc to get the book.");

        Room r5 = new Room("You are in room 5",
                "The air feels warm and humid, and an intense smell of fire and soot" +
                        " stings your nose. A dragon is guarding a treasure chest filled with gold.\n" +
                        "Defeat the dragon to collect the gold and win the game!");

        Room r6 = new Room("you are in room 6",
                "The relaxing sound of flowing water meets your ears. " +
                        "A bridge is crossing a black lake. Across the bridge you will find" +
                        " a golden bejewelled archbow with five arrows.");

        Room r7 = new Room("You are in room 7",
                "Spiderwebs are tangled throughout the room, and clings to" +
                        " your face. An empty water container is lying on the floor." +
                        " You might need it to collect water in other rooms.");

        Room r8 = new Room("You are in room 8",
                "The temperature in this room is rising. " +
                        "A rusty key is kept inside a pale glowing mushroom.\n" +
                        "Eat the mushroom to access the key.");

        Room r9 = new Room("You are in room 9",
                "");

        // Create items
        Item key = new Item("key", "a rusty key inside a pale glowing mushroom");
        // Item sword = new Item("sword", "a long sword");
        // Item bludgeon = new Item( "bludgeon", "a dusty, knobbed bludgeon");
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
        Weapon bludgeon = new MeleeWeapon("bludgeon", "a dusty, knobbed bludgeon",
                10);

        // Assign items and food items to rooms + Water Source(true/false) + Sword Tree (true/false)
        r1.addItem(bludgeon);

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
        r9.setBreadCreature(true);

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