package machine;

public enum CoffeeTypes {

    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350, 75, 20, 7),
    CAPPUCCINO(3, 200, 100, 12, 6);

    private final int index;
    private final int waterCost;
    private final int milkCost;
    private final int beanCost;
    private final int price;
    private final int cupCost = 1;


    CoffeeTypes(int index, int water, int milk, int beans, int price) {
        this.index = index;
        this.waterCost = water;
        this.milkCost = milk;
        this.beanCost = beans;
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public int[] getCosts(){
        return new int[] {waterCost, milkCost, beanCost, cupCost};
    }

    public String[] getCostNames() {
        return new String[] {"water", "milk", "beans", "cups"};
    }

    public int getPrice(){
        return price;
    }

    public static CoffeeTypes valueOfIndex(int choice) {
        for (CoffeeTypes coffeeChoice : values()) {
            if (coffeeChoice.getIndex() == choice) {
                return coffeeChoice;
            }
        }
        return null;
    }


}
