public class EquipOutcome {
    private EquipResult result;
    private String itemName;

    public EquipOutcome(EquipResult result, String itemName) {
        this.result = result;
        this.itemName = itemName;
    }

    public EquipResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }
}
