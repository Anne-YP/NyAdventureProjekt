public class Item {

        private String shortName;
        private String longName;

    public Item(String shortName, String longName) {
        Item key = new Item("Key", "A rusty key in a glass jar");
        Item sword = new Item("Sword", "A long sword");
        Item lamp = new Item("Lamp", "An ancient oil lamp");
        Item archbow = new Item("Archbow", "long name");
        Item arrows = new Item("Arrows", "A case with five arrows");
        Item gold = new Item("Gold", "A treasure chest filled with gold");
        Item food = new Item("Food", "A loaf of bread");
        Item book = new Item("Book", "A book: 'How to defeat a dragon'");

    }
}
