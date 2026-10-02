public class Food extends Consumable{

    public Food(String shortName, String longName, int healthPoints) {
        super(shortName, longName, healthPoints);
    }

    @Override
    public String getConsumeVerb() {
        return "ate";
    }
}
