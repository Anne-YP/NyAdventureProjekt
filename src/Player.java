import java.util.ArrayList;
public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;
    private Weapon equippedWeapon;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        inventory = new ArrayList<>();
        health = 100;
    }

    // Rooms
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

    // Inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }
    public void removeItem(Item item) {
        inventory.remove(item);

        if(item == equippedWeapon) {
            equippedWeapon = null;
        }
    }

    public Item findItem(String shortName) {
        for(Item item: inventory) {
            if(item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }


    public EatOutcome eat(String itemName) {

        boolean foundInInventory = true;
        Item item = findItem(itemName);

        if(item == null) {
            foundInInventory = false;
            item = currentRoom.findItem(itemName);
        }

        if(item == null) {
            return new EatOutcome(EatResult.NOT_FOUND, itemName, 0, "");
        }

        if(!(item instanceof Consumable)) {
            return new EatOutcome(EatResult.NOT_EDIBLE, item.getLongName(), 0, "");
        }

        Consumable consumable = (Consumable) item;
        changeHealth(consumable.getHealthPoints());

        if(foundInInventory) {
            removeItem(item);
        }
        else {
            currentRoom.removeItem(item);
        }
        if (item.getShortName().equals("mushroom")) {
            currentRoom.setMushroomEaten(true);
        }
        return new EatOutcome(EatResult.SUCCESS, item.getLongName(), 0,
                consumable.getConsumeVerb());
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

    public void changeHealth(int amount) {
        health += amount;
    }

    // Weapon-getter
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public EquipOutcome equip(String itemName) {
        Item item = findItem(itemName);

        if(item == null) {
            return new EquipOutcome(EquipResult.NOT_FOUND, itemName);
        }

        if(!(item instanceof  Weapon)) {
            return new EquipOutcome(EquipResult.NOT_A_WEAPON, item.getLongName());
        }

        equippedWeapon = (Weapon) item;
        return new EquipOutcome(EquipResult.SUCCESS, item.getLongName());
    }

    public AttackOutcome attack() {
        if(equippedWeapon == null) {
            return new AttackOutcome(AttackResult.NO_WEAPON_EQUIPPED, null);
        }

        if(!equippedWeapon.canUse()) {
            return new AttackOutcome(AttackResult.WEAPON_EMPTY, equippedWeapon);
        }

        equippedWeapon.use();
        return new AttackOutcome(AttackResult.SUCCESS, equippedWeapon);
    }

}

