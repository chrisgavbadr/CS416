import java.util.Scanner;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class InputOutput {

    public static final double FLOAT_THRESHOLD = 0.00000001;
    private Scanner scan = new Scanner(System.in);

    /**
     * InputOutput constructor.
     */
    public InputOutput() {
        System.out.println("Enter 1 or 2");

        int input = scan.nextInt();

        if (input == 1) {
            tipCalculator();
        } else if (input == 2) {
            changeMachine();
        }
    }

    /**
     * @param args [String[]] String[] args parameter for main method
     */
    public static void main(String[] args) {
        new InputOutput();
    }

    /**
     * Calculates tip based on bill and tip percentage.
     */
    public void tipCalculator() {
        System.out.println("Enter bill amount: ");
        double bill = scan.nextDouble();
        System.out.println("Percentage to tip: ");
        double tipPercentage = scan.nextDouble();
        System.out.println("Number of people: ");
        int people = scan.nextInt();

        double tipAmount = bill * (tipPercentage / 100);
        double totalAmount = bill + tipAmount;

        System.out.printf("\nTip amount: $%.2f\n", tipAmount);
        System.out.printf("Total amount: $%.2f\n", totalAmount);
        System.out.printf("\nTip per person: $%.2f\n", tipAmount / people);
        System.out.printf("Total per person: $%.2f\n", totalAmount / people);
    }

    /**
     * Determines number of dollars, quarters, dimes, nickels, and pennies to output change due.
     */
    public void changeMachine() {
        System.out.println("Total amount? ");
        double totalAmount = scan.nextDouble();
        System.out.println("Cash payment? ");
        int cashPayment = scan.nextInt();
        double changeDue = cashPayment - totalAmount;
        System.out.printf("\nChange Due $%.2f\n\n", changeDue);

        double[] change = {1, 0.25, 0.1, 0.05, 0.01};
        int[] counts = {0, 0, 0, 0, 0};
        int index = 0;
        while (Math.abs(changeDue) >= FLOAT_THRESHOLD) {
            if (change[index] <= changeDue + FLOAT_THRESHOLD) {
                changeDue -= change[index];
                counts[index]++;
            } else {
                index++;
            }
        }

        System.out.println("Dollars: " + counts[0]);
        System.out.println("Quarters: " + counts[1]);
        System.out.println("Dimes: " + counts[2]);
        System.out.println("Nickels: " + counts[3]);
        System.out.println("Pennies: " + counts[4]);
    }
}
