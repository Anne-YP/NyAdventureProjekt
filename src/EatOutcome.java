public class EatOutcome {
    private EatResult result;
    private String itemName;
    private int healthChange;
    private String verb;

    public EatOutcome(EatResult result, String itemName, int healthChange, String verb) {
        this.result = result;
        this.itemName = itemName;
        this.healthChange = healthChange;
        this.verb = verb;
    }

    public EatResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealthChange() {
        return healthChange;
    }

    public String getVerb() {
        return verb;
    }
}
