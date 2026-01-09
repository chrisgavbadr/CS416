import java.util.Scanner;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class PayStub {

    // Global constants used for calculation
    private static final int CURRENT_PAY_MONTH = 1;
    private static final int CURRENT_PAY_YEAR = 2019;
    private static final double VACATION_HOURS_PER_MONTH = 8.25;
    private static final double RETIREMENT_RATE = 0.052;
    private static final double TAX_RATE = 0.28;

    // Data fields about employee
    private String name;
    private String jobTitle;
    private int anniversaryMonth;
    private int anniversaryYear;
    private int hoursWorked;
    private int monthsWorked;
    private double vacationHours;
    private double hourlyPayRate;
    private double grossPay;
    private double netPay;
    private double retirementWithholding;
    private double taxWithholding;

    /**
     * @param name             [String] Name of employee
     * @param anniversaryMonth [int] Anniversary month (1-12)
     * @param anniversaryYear  [int] Anniversary year
     * @param hours            [int] Numbers of hours worked
     * @param title            [String] Job title of employee
     * @param payRate          [double] Hourly pay rate
     */
    // Constructs employee profile based on listed parameters
    public PayStub(String name, int anniversaryMonth, int anniversaryYear, int hours, String title, double payRate) {
        this.name = name;
        this.jobTitle = title;
        this.anniversaryMonth = anniversaryMonth;
        this.anniversaryYear = anniversaryYear;
        this.hoursWorked = hours;
        this.hourlyPayRate = payRate;
        numMonthsWorked();
        vacationHours();
        grossPay();
        retHold();
        tax();
        netPay = grossPay - (retirementWithholding + taxWithholding);
    }

    /**
     * Initializes data members.
     */
    public PayStub() {
    }

    /**
     * @param args [String[]] String[] args parameter for main method
     */
    // Prompts user to create an employee profile and display its info
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your Fullname: ");
        String name = s.nextLine();
        System.out.print("Enter your Anniversary Month(1-12): ");
        int anniversaryMonth = s.nextInt();
        System.out.print("Enter your Anniversary Year: ");
        int anniversaryYear = s.nextInt();
        System.out.print("Enter your hours worked this pay period(0-350): ");
        int hoursWorked = s.nextInt();
        System.out.print("Enter your Job Title: ");
        s.nextLine();
        String jobTitle = s.nextLine();
        System.out.print("Enter your pay rate: ");
        double hourlyPayRate = s.nextDouble();

        PayStub employee = new PayStub(name, anniversaryMonth, anniversaryYear, hoursWorked, jobTitle, hourlyPayRate);
        employee.printInfo();
    }

    /**
     * @return [String] Name of employee
     */
    public String getName() {
        return name;
    }

    /**
     * @return [String] Job title of employee
     */
    public String getTitle() {
        return jobTitle;
    }

    /**
     * @return [int] Anniversary month (1-12)
     */
    public int getMonth() {
        return anniversaryMonth;
    }

    /**
     * @return [int] Anniversary year
     */
    public int getYear() {
        return anniversaryYear;
    }

    /**
     * @return [int] Number of hours worked
     */
    public int getHours() {
        return hoursWorked;
    }

    /**
     * @return [double] Hourly pay rate
     */
    public double getPayRate() {
        return hourlyPayRate;
    }

    /**
     * @return [int] Number of months worked
     */
    // Calculates the number of months worked
    public int numMonthsWorked() {
        monthsWorked = 12 * (CURRENT_PAY_YEAR - anniversaryYear) + (CURRENT_PAY_MONTH - anniversaryMonth);
        return monthsWorked;
    }

    /**
     * @return [double] Numbers of vacation hours earned
     */
    // Calculates the number of vacation hours earned this pay period
    public double vacationHours() {
        vacationHours = VACATION_HOURS_PER_MONTH * monthsWorked;
        return vacationHours;
    }

    /**
     * @return [double] Gross pay of employee
     */
    // Calculates the gross pay of employee
    public double grossPay() {
        grossPay = hoursWorked * hourlyPayRate;
        return grossPay;
    }

    /**
     * @return [double] Retirement withholding
     */
    // Calculates retirement withholding
    public double retHold() {
        retirementWithholding = RETIREMENT_RATE * grossPay;
        return retirementWithholding;
    }

    /**
     * @return [double] Tax withholding
     */
    // Calculates tax withholding
    public double tax() {
        taxWithholding = TAX_RATE * (grossPay - retirementWithholding);
        return taxWithholding;
    }

    /**
     * Draws Gekko & Co. logo.
     */
    public void drawLogo() {
        System.out.println("     Gekko & Co.\n"
                + "\n"
                + "          \"$\"\n"
                + "          ~~~\n"
                + "         /  \\ `.\n"
                + "        /    \\  /\n"
                + "       /_ _ _ \\/");
    }

    /**
     * Prints of employee's information.
     */
    public void printInfo() {
        System.out.println("==========================================");
        drawLogo();
        System.out.println("------------------------------------------");
        System.out.printf("Pay Period:\t %d/%d\n", CURRENT_PAY_MONTH, CURRENT_PAY_YEAR);
        System.out.printf("Name:\t\t %s\n", name);
        System.out.printf("Title:\t\t %s\n", jobTitle);
        System.out.printf("Anniversary:\t %d/%d\n", anniversaryMonth, anniversaryYear);
        System.out.printf("Months Worked:\t %d months\n", monthsWorked);
        System.out.printf("Vacation hours:\t %.2f\n", vacationHours);
        System.out.println("------------------------------------------");
        System.out.printf("Gross Pay:\t$%7.2f\n", grossPay);
        System.out.printf("Retirement:\t$%7.2f\n", retirementWithholding);
        System.out.printf("Tax:\t\t$%7.2f\n", taxWithholding);
        System.out.println("------------------------");
        System.out.printf("Net Pay:\t$%7.2f\n", netPay);
        System.out.println("==========================================");
    }
}
