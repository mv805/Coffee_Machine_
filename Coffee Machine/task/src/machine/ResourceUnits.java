package machine;

public enum ResourceUnits {
    WATER("ml", "water"),
    MILK("ml", "milk"),
    BEANS("grams", "coffee beans"),
    CUPS("disposable cups", "coffee");

    private final String unit;
    private final String displayName;

    ResourceUnits(String unit, String displayName){
        this.unit = unit;
        this.displayName = displayName;
    }

    public String getUnit(){
        return unit;
    }

    public String getDisplayName(){
        return displayName;
    }
}
