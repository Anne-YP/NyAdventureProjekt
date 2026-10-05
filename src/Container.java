public class Container extends Item{
    private Liquid content;

    public Container (String shortName, String longName) {
        super(shortName, longName);
    }

    public boolean isFilled(){
        return content != null;
    }

    public void fill(Liquid liquid) {
        content = liquid;
    }
    public void empty() {
        content = null;
    }
    public Liquid getContent() {
        return content;
    }
}
