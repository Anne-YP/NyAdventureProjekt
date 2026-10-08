public class RangedWeapon extends Weapon{

    private int ammunition;

    public RangedWeapon (String shortName, String longName, int damage, int ammunition, String attackVerb) {
        super(shortName, longName, damage, attackVerb);
        this.ammunition = ammunition;
    }

    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    @Override
    public void use() {
        if (ammunition > 0) {
            ammunition--;
        }
    }

    @Override
    public String getAttackVerb() {
        return "shoot";
    }

    @Override
    public String getUsesLeftText() {
     if (ammunition == 1) {
         return " 1 arrow left.";
     }
        return " " + ammunition + " arrows left.";
    }

    @Override
    public String getAttackNoun() {
        return "arrow";
    }
}
