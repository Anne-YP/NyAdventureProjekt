public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String shortName, String longName, int damage, String attackVerb) {
        super(shortName, longName,damage, attackVerb);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
    }

    @Override
    public String getAttackVerb() {
        return "swing";
    }

    @Override
    public String getUsesLeftText() {
        return "";
    }
}
