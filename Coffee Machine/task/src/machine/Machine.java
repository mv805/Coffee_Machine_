package machine;

import java.util.Scanner;

public class Machine {

    private static Coffee espresso = new Coffee("espresso", 250,
            0, 16, 4);
    private static Coffee latte = new Coffee("latte", 350,
            75, 20, 7);
    private static Coffee cappuccino = new Coffee("cappuccino", 200,
            100, 12, 6);

    public static final Scanner scanner = new Scanner(System.in);

    MachineState machineState = MachineState.PROMPTING;

    public static int machineWater = 400;
    public static int machineMilk = 540;
    public static int machineBeans = 120;
    public static int machineMoney = 550;
    public static int machineCups = 9;

    public boolean machineOn = true;

    public void printMachineStats() {

        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", machineWater);
        System.out.printf("%d ml of milk%n", machineMilk);
        System.out.printf("%d g of coffee beans%n", machineBeans);
        System.out.printf("%d disposable cups%n", machineCups);
        System.out.printf("$%d of money%n%n", machineMoney);

    }

    private void fillMachine() {

        System.out.println("Write how many ml of water you want to add:");
        machineWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        machineMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you " +
                "want to add:");
        machineBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee " +
                "you want to add:");
        machineCups += scanner.nextInt();
        System.out.println();
    }

    private void changeState() {

        String userInput = "PROMPTING";

        switch (machineState) {
            case PROMPTING:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                userInput = scanner.next().toUpperCase();
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, " +
                        "2 - latte, 3 - cappuccino:");

                String coffeeChoice = scanner.next().toLowerCase();

                if (coffeeChoice.equalsIgnoreCase("back")) {
                    machineState = MachineState.PROMPTING;
                    return;
                } else {
                    buyCoffee(Integer.parseInt(coffeeChoice));
                }
                break;
            case TAKE:
                takeMoney();
                break;
            case FILL:
                fillMachine();
                break;
            case REMAINING:
                printMachineStats();
                break;
            case EXIT:
                machineOn = false;
                break;
        }

        machineState = MachineState.valueOf(userInput);
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d", machineMoney);
        machineMoney = 0;
    }

    public void operate() {
        changeState();
    }

    private static void buyCoffee(int coffeeChoice) {

        Coffee coffeeChosen;

        switch (coffeeChoice) {
            case 1:
                coffeeChosen = Machine.espresso;
                break;
            case 2:
                coffeeChosen = Machine.latte;
                break;
            case 3:
                coffeeChosen = Machine.cappuccino;
                break;
            default:
                coffeeChosen = new Coffee("default", 0,
                        0, 0, 0);
                break;
        }

        //make sure enough ingredients remain
        if (machineWater < coffeeChosen.waterCost) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return;
        } else if (machineMilk < coffeeChosen.milkCost) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return;
        } else if (machineBeans < coffeeChosen.beanCost) {
            System.out.println("Sorry, not enough beans!");
            System.out.println();
            return;
        } else if (machineCups == 0) {
            System.out.println("Sorry, not enough cups!");
            System.out.println();
            return;
        }

        machineMilk -= coffeeChosen.milkCost;
        machineBeans -= coffeeChosen.beanCost;
        machineWater -= coffeeChosen.waterCost;
        machineMoney += coffeeChosen.price;
        machineCups--;

        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();
    }

    enum MachineState {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT,
        PROMPTING
    }
}
