package machine;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CoffeeMachine {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Machine machine = new Machine();

        while (machine.machineOn){
            machine.operate();
        }
    }


}
