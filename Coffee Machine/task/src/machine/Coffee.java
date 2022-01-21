package machine;

public class Coffee {

    public final String type;
    public final int waterCost;
    public final int milkCost;
    public final int beanCost;
    public final int price;

    public Coffee(String type, int waterCost, int milkCost,
                  int beanCost, int price) {

        this.type = type;
        this.waterCost = waterCost;
        this.milkCost = milkCost;
        this.beanCost = beanCost;
        this.price = price;
    }
}
