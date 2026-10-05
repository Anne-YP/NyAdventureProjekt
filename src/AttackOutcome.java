public class AttackOutcome {

    private AttackResult result;
    private Weapon weapon;

    public AttackOutcome(AttackResult result, Weapon weapon) {
        this.result = result;
        this.weapon = weapon;
    }

    public AttackResult getResult() {
        return result;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
