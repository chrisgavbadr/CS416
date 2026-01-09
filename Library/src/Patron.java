import java.util.ArrayList;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Patron implements Printable {

    private String name;
    private int cardNumber;
    private ArrayList<Item> items;

    /**
     * Constructor for Patron class.
     *
     * @param name       [String] Name of patron
     * @param cardNumber [int] Card number of patron
     */
    public Patron(String name, int cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
        items = new ArrayList<Item>();
    }

    /**
     * Returns string displaying name and card number of patron.
     *
     * @return [String] Name and card number of patron
     */
    @Override
    public String basicInfo() {
        return String.format("%s (%d)", name, cardNumber);
    }

    /**
     * Returns string displaying name of, card number of, and items checked out by patron.
     *
     * @return [String] Name of, card number of, and items checked out by patron.
     */
    @Override
    public String detailedInfo() {
        StringBuilder info = new StringBuilder(String.format("Name: %s\n", name)
                + String.format("Card number: %d", cardNumber));
        for (Item item : items) {
            info.append("\n    ").append(item.basicInfo()).append(" due on ").append(item.getDueDate());
        }
        return info.toString();
    }

    /**
     * Returns list of items checked out by patron.
     *
     * @return [ArrayList[Item]] List of items checked out by patron
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
