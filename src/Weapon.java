public abstract class Weapon extends Item {

    private int damage;
    private String attackVerb;

    public Weapon(String shortName, String longName, int damage, String attackVerb) {
        super(shortName, longName);
        this.damage = damage;
        this.attackVerb = attackVerb;
    }
    public int getDamage() {
        return damage;
    }

    public abstract boolean canUse();

    public abstract void use();

    public abstract String getUsesLeftText ();

    public String getAttackNoun() {
        return getShortName();
    }

    public String getAttackVerb() {
        return attackVerb;
    }
}
