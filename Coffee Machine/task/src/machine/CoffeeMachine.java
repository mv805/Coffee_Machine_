package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        /*System.out.printf("Starting to make a coffee%n" +
                "Grinding coffee beans%n" +
                "Boiling water%n" +
                "Mixing boiled water with crushed coffee beans%n" +
                "Pouring coffee into the cup%n" +
                "Pouring some milk into the cup%n" +
                "Coffee is ready!");*/
        System.out.println("Write how many cups of coffee you will need:");

        int cupsNeeded = scanner.nextInt();
        int[] ingredients = ingredientsNeeded(cupsNeeded);

        System.out.printf("For %d cups of coffee you will need:%n", cupsNeeded);
        System.out.printf("%d ml of water%n", ingredients[0]);
        System.out.printf("%d ml of milk%n", ingredients[1]);
        System.out.printf("%d g of coffee beans%n", ingredients[2]);
    }

    public static int[] ingredientsNeeded(int cupsNeeded){

        //quantities needed per cup of coffee
        final int WATER_NEEDED = 200;
        final int MILK_NEEDED = 50;
        final int BEANS_NEEDED = 15;

        int totalWater = WATER_NEEDED * cupsNeeded;
        int totalMilk = MILK_NEEDED * cupsNeeded;
        int totalBeans = BEANS_NEEDED * cupsNeeded;

        return new int[]{totalWater, totalMilk, totalBeans};
    }
}
