public class Enemy {
    private String shortName;
    private String longName;
    private String description;

    private int health;

    private Weapon weapon;

    private Room currentRoom;

    public Enemy(String shortName, String longName,
                 String description, int health, Weapon weapon, Room currentRoom) {

        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean hit(Weapon weapon) {
        health -= weapon.getDamage();

        if(health <= 0) {
            currentRoom.addItem(this.weapon);
            currentRoom.removeEnemy(this);

            return true;
        }
        return false;
    }

    public boolean attack(Player player) {

    if(!weapon.canUse()) {
        return false;
    }
    player.changeHealth(-weapon.getDamage());
    weapon.use();
    return true;
    }
}
