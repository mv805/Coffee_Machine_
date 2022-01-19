package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //quantities needed per cup of coffee
        final int WATER_NEEDED_PER_CUP = 200;
        final int MILK_NEEDED_PER_CUP = 50;
        final int BEANS_NEEDED_PER_CUP = 15;
        int[] ingredientsNeededPerCup = {WATER_NEEDED_PER_CUP, MILK_NEEDED_PER_CUP,
                BEANS_NEEDED_PER_CUP};

        System.out.println("Write how many ml of water the coffee machine has:");
        int totalWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int totalMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee" +
                "machine has:");
        int totalBeans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int cupsNeeded = scanner.nextInt();

        int cupsBrewed = cupsBrewed(cupsNeeded, totalWater, totalMilk, totalBeans,
                ingredientsNeededPerCup);

        if (cupsBrewed < cupsNeeded) {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", cupsBrewed);
        } else if (cupsBrewed > cupsNeeded) {
            System.out.printf("Yes, I can make that amount of coffee (and even" +
                    " %d more than that)%n", cupsBrewed - cupsNeeded);
        } else if (cupsBrewed == cupsNeeded) {
            System.out.printf("Yes, I can make that amount of coffee%n");
        }
    }

    public static int cupsBrewed(int cupsNeeded, int machineWater,
                                 int machineMilk, int machineBeans, int[] ingrPerCup) {

        int[] ingredientsOnHand = {machineWater, machineMilk, machineBeans};

        int cupsBrewed = ingredientsOnHand[0] / ingrPerCup[0];

        for (int i = 1; i < ingredientsOnHand.length; i++) {
            if (ingredientsOnHand[i] / ingrPerCup[i] < cupsBrewed) {
                cupsBrewed = ingredientsOnHand[i] / ingrPerCup[i];
            }
        }

        return cupsBrewed;
    }
}
