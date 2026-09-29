public class Map {
    // Create Map for Rooms
    private Room startingRoom;
    //
    public Map() {
        // create rooms
        Room r1 = new Room("You are in room 1",
                "A room with no distinct features, except two doors.");

        Room r2 = new Room("You are in room 2",
                "Water drips from the ceiling somewhere in the dark.");

        Room r3 = new Room("You are in room 3",
                "There is a tree growing in a ray of light. A sword is hanging from one of its branches.");

        Room r4 = new Room("You are in room 4",
                "There is a orc standing in front of you. What do you do?");

        Room r5 = new Room("You are in room 5",
                "THIS IS A SPECIAL ROOM");

        Room r6 = new Room("you are in room 6",
                "There is a bridge crossing a black lake.");

        Room r7 = new Room("You are in room 7",
                "There is an empty water container lying on the floor.");

        Room r8 = new Room("You are in room 8",
                "A rusty key is kept in a glass jar. Do you want to collect it?");

        Room r9 = new Room("You are in room 9",
                "There is a mythical creature offering you bread.");


// create items
        Item key = new Item("key", "A rusty key in a glass jar");
        Item sword = new Item("sword", "A long sword");
        Item lamp = new Item("lamp", "An ancient oil lamp");
        Item archbow = new Item("archbow", "long name");
        Item arrows = new Item("arrows", "A case with five arrows");
        Item gold = new Item("gold", "A treasure chest filled with gold");
        Item food = new Item("food", "A loaf of bread");
        Item book = new Item("book", "A book: 'How to defeat a dragon'");
        Item container = new Item("container", "A water container");

        // assign items to rooms
        r2.addItem(lamp);
        r2.addItem(book);

        r3.addItem(sword);

        r4.addItem(arrows);

        r5.addItem(gold);

        r6.addItem(archbow);

        r7.addItem(container);

        r8.addItem(key);

        r9.addItem(food);

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
}