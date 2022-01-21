package machine;

import java.util.Scanner;

public class Machine {

    public static final Scanner scanner = new Scanner(System.in);

    MachineState machineState = MachineState.PROMPTING;

    public static int machineMoney = 550;
    //machineIngredients order needs to match order of CoffeeTypes costs order.
    public static int[] machineIngredients = {400, 540, 120, 9};

    public boolean machineOn = true;

    public void printMachineStats() {

        int index = 0;
        System.out.println("The coffee machine has:");
        for (ResourceUnits resources: ResourceUnits.values()) {
            System.out.printf("%d %s of %s%n", machineIngredients[index],
                    resources.getUnit(), resources.getDisplayName());
            index++;
        }
        System.out.printf("$%d of money%n%n", machineMoney);
    }

    private void fillMachine() {
        int index = 0;
        for (ResourceUnits resources: ResourceUnits.values()) {
            System.out.printf("Write how many %s of %s you want to add:%n",
                    resources.getUnit(), resources.getDisplayName());
            machineIngredients[index] += scanner.nextInt();
            index++;
        }
        System.out.println();
    }

    private void changeState() {

        String userInput = "PROMPTING";

        switch (machineState) {
            case PROMPTING:
                printPromptMenu();
                userInput = scanner.next().toUpperCase();
                break;
            case BUY:
                printBuyMenu();
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

    private void printPromptMenu() {
        System.out.print("Write action (");
        for (MachineState state: MachineState.values()) {
           if (state.name().equals("PROMPTING")){
               break;
           }
           else if (state.ordinal() == MachineState.values().length - 2){
               System.out.printf("%s):%n", state.name().toLowerCase());
               break;
           }
           System.out.printf("%s, ", state.name().toLowerCase());

        }
    }

    private void printBuyMenu() {
        System.out.print("What do you want to buy? ");

        for (CoffeeTypes coffeeType : CoffeeTypes.values()) {
            System.out.printf("%d - %s, ",coffeeType.getIndex(),coffeeType.name().toLowerCase());
        }

        System.out.print("back - to main menu:");
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d", machineMoney);
        machineMoney = 0;
    }

    public void operate() {
        changeState();
    }

    private static void buyCoffee(int coffeeChoice) {

        CoffeeTypes coffee = CoffeeTypes.valueOfIndex(coffeeChoice);
        String[] costNames = coffee.getCostNames();
        int[] coffeeCosts = coffee.getCosts();

        for (int i = 0; i < coffeeCosts.length; i++){
            if (machineIngredients[i] < coffeeCosts[i]){
                System.out.printf("Sorry, not enough %s!%n", costNames[i]);
                return;
            }
        }

        for (int i = 0; i < coffeeCosts.length; i++){
            machineIngredients[i] -= coffeeCosts[i];
        }

        machineMoney += coffee.getPrice();

        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();
    }


    enum MachineState {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT,
        PROMPTING //prompting must remain last option
    }
}
