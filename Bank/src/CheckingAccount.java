/**
 * @author Christian Baduria
 * @version 1.0
 */

public class CheckingAccount implements Account {

    private int balance;
    private String id;
    private String name;

    /**
     * Constructs CheckingAccount object.
     *
     * @param id              [String] Unique ID of bank account
     * @param name            [String] Name of bank account holder
     * @param startingBalance [int] Balance of bank account
     */
    public CheckingAccount(String id, String name, int startingBalance) {
        balance = startingBalance;
        this.id = id;
        this.name = name;
    }

    /**
     * Deposits some amount into bank account.
     *
     * @param amount [int] Amount to deposit into account (a nonnegative integer)
     */
    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * Withdraws some amount from bank account if possible.
     *
     * @param amount [int] Amount to withdraw from account (a nonnegative integer)
     * @return [boolean] Whether or not withdrawal was successful
     */
    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Returns balance of bank account.
     *
     * @return [int] Balance of bank account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns ID of bank account.
     *
     * @return [String] Unique ID of bank account
     */
    public String getId() {
        return id;
    }

    /**
     * Returns name of bank account holder.
     *
     * @return [String] Name of bank account holder
     */
    public String getName() {
        return name;
    }

    /**
     * Returns String of information about CheckingAccount.
     *
     * @return [String] Information about CheckingAccount
     */
    @Override
    public String toString() {
        return String.format("%s %s $%d", id, name, balance);
    }
}
