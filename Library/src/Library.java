import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a library and providing an interactive user interface for
 * checking items in and out of the library.
 *
 * @author Christian Baduria
 * @version 1.0
 */
public class Library {

    private ArrayList<Item> catalog;
    private ArrayList<Item> searchResults;
    private ArrayList<Patron> patrons;
    private Patron currentPatron;
    private Scanner input;
    private LocalDate currentDate;
    private boolean isOpen;

    /**
     * Constructor for Library class.
     *
     * @param catalog [ArrayList[Item]] Catalog of all available items
     * @param input   [Scanner] Source of user input
     * @param year    [int] Current year
     * @param month   [int] Current month
     * @param day     [int] Current day
     */
    public Library(ArrayList<Item> catalog, Scanner input, int year, int month, int day) {
        this.catalog = catalog;
        searchResults = new ArrayList<Item>();
        patrons = new ArrayList<Patron>();
        currentPatron = null;
        this.input = input;
        currentDate = LocalDate.of(year, month, day);
        isOpen = false;
    }

    /**
     * Returns complete library catalog of all available items.
     *
     * @return [ArrayList[Item]] Complete library catalog of all available items
     */
    public ArrayList<Item> getCatalog() {
        ArrayList<Item> availableItems = new ArrayList<Item>();
        for (Item item : catalog) {
            if (!item.isCheckedOut()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    /**
     * Returns current date stored for library.
     *
     * @return [LocalDate] Current date stored for library
     */
    public LocalDate getCurrDate() {
        return currentDate;
    }

    /**
     * Opens library for day, modifying instance variables to represent library being open.
     *
     * @return [String] String containing information about overdue items
     */
    public String open() {
        isOpen = true;
        StringBuilder overdueItems = new StringBuilder("Overdue items:");
        for (Patron patron : patrons) {
            for (Item item : patron.getItems()) {
                if (currentDate.isAfter(item.getDueDate())) {
                    overdueItems.append("\n    ").append(patron.basicInfo()).append(": ");
                    overdueItems.append(item.basicInfo()).append(" was due on ").append(item.getDueDate());
                }
            }
        }
        return overdueItems.toString();
    }

    /**
     * Closes library for day, clears current patron, and updates current date.
     */
    public void close() {
        isOpen = false;
        currentPatron = null;
        currentDate = currentDate.plusDays(1);
    }

    /**
     * Returns true if library is currently open, false otherwise.
     *
     * @return [boolean] Whether or not library is open
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Creates new patron, sets it as current patron, and adds it to patron list.
     *
     * @param name       [String] Name of patron
     * @param cardNumber [int] Card number of patron
     */
    public void addPatron(String name, int cardNumber) {
        currentPatron = new Patron(name, cardNumber);
        patrons.add(currentPatron);
    }

    /**
     * Returns complete list of all patrons.
     *
     * @return [ArrayList[Patron]] Complete list of all patrons
     */
    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    /**
     * Sets current patron from patron list.
     *
     * @param index [int] Index of patron, starting from 1
     */
    public void setPatron(int index) {
        currentPatron = patrons.get(index - 1);
    }

    /**
     * Returns patron currently being served, or null if no patron has been selected.
     *
     * @return [Patron] Patron currently being served
     */
    public Patron getCurrPatron() {
        return currentPatron;
    }

    /**
     * Performs search for any items which contain search text. Stores results of search in field within library object.
     *
     * @param search [String] Search query
     */
    public void search(String search) {
        searchResults.clear();
        for (Item item : catalog) {
            if (item.contains(search)) {
                searchResults.add(item);
            }
        }
    }

    /**
     * Returns results from most recent search, or null if no results are currently stored.
     *
     * @return [ArrayList[Item]] Results from most recent search
     */
    public ArrayList<Item> getSearchResults() {
        return searchResults;
    }

    /**
     * Checks in item from current patron's checked out items.
     *
     * @param index [int] Index of item, starting from 1
     */
    public void checkIn(int index) {
        catalog.add(currentPatron.getItems().remove(index - 1));
        catalog.get(catalog.size() - 1).checkIn();
    }

    /**
     * Checks out item from most recent search result list.
     *
     * @param index [int] Index of item, starting from 1
     */
    public void checkOut(int index) {
        Item selection = searchResults.remove(index - 1);
        catalog.remove(selection);
        selection.checkOut(currentDate);
        currentPatron.getItems().add(selection);
    }

    /**
     * Starts interactive library program.
     */
    public void start() {
        System.out.println("Enter o to open the library or x to exit the program.");
        String action = input.next();
        while (action.equals("o")) {
            patronLoop();
            System.out.println("Enter o to open the library or x to exit the program.");
            action = input.next();
        }
    }

    /**
     * Loop that deals with patron creation and patron selection.
     */
    public void patronLoop() {
        System.out.printf("Today\'s date: %s\n\n", currentDate.toString());
        System.out.println(open());
        String action;
        do {
            System.out.println("Choose an existing patron by number, "
                    + "enter n to add a new patron, or enter c to close.");
            for (int i = 0; i < patrons.size(); i++) {
                System.out.printf("%d: %s\n", i + 1, patrons.get(i).basicInfo());
            }
            action = input.next();
            if (action.equals("n")) {
                System.out.println("Enter patron name.");
                input.nextLine();
                String name = input.nextLine();
                System.out.println("Enter patron card number.");
                int cardNumber = input.nextInt();
                patrons.add(new Patron(name, cardNumber));
                setPatron(patrons.size());
                checkInOutItemLoop();
            } else if (!action.equals("c")) {
                setPatron(Integer.parseInt(action));
                System.out.println("\n" + currentPatron.detailedInfo());
                checkInOutItemLoop();
            }
        } while (!action.equals("c"));
        close();
    }

    /**
     * Loop that deals with searching, checking in, and checking out items.
     */
    public void checkInOutItemLoop() {
        String action;
        do {
            System.out.println("\nEnter s to search for an item to check out, "
                    + "i to check in an item, or m to return to the previous menu.");
            action = input.next();
            if (action.equals("s")) {
                System.out.println("Enter search text.");
                input.nextLine();
                search(input.nextLine());
                System.out.println("\nResults:");
                for (int i = 0; i < searchResults.size(); i++) {
                    System.out.printf("%d: %s\n", i + 1, searchResults.get(i).basicInfo());
                }
                System.out.println("\nItem number:");
                int itemNumber = input.nextInt();
                System.out.println("\n" + searchResults.get(itemNumber - 1).detailedInfo());
                System.out.println("\nDo you want to check out this item? (Enter y or n)");
                String in = input.next();
                if (in.equals("y")) {
                    checkOut(itemNumber);
                }
            } else if (action.equals("i")) {
                for (int i = 0; i < currentPatron.getItems().size(); i++) {
                    System.out.printf("%d: %s due on %s\n", i + 1, currentPatron.getItems().get(i).basicInfo(),
                            currentPatron.getItems().get(i).getDueDate().toString());
                }
                System.out.println("\nItem to check in:");
                checkIn(input.nextInt());
            }
        } while (!action.equals("m"));
    }

    /////////////////// DO NOT CHANGE THE CODE BELOW /////////////////////

    /**
     * Utility method for reading the entire library catalog from input.
     * Input file must give the type of item first (BOOK, MUSIC, or VIDEO.)
     * Includes a blank line after each item, and END to signify the end of the data.
     *
     * @param fileName Location of input file.
     * @return The catalog.
     */
    public static ArrayList<Item> readCatalog(String fileName) {

        Scanner input = null;
        try {
            input = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            System.exit(-1);
        }

        String line = "";
        ArrayList<Item> catalog = new ArrayList<Item>();
        boolean stop = false;
        while (!stop) {
            line = input.nextLine();
            switch (line) {
                case "BOOK":
                    String title = input.nextLine();
                    String author = input.nextLine();
                    int pages = input.nextInt();
                    int year = input.nextInt();
                    catalog.add(new Book(title, author, pages, year));
                    input.nextLine();
                    break;
                case "MUSIC":
                    title = input.nextLine();
                    String artist = input.nextLine();
                    String format = input.nextLine();
                    year = input.nextInt();
                    input.nextLine();
                    ArrayList<String> tracks = new ArrayList<String>();
                    while (true) {
                        line = input.nextLine();
                        if (line.equals("")) {
                            break;
                        }
                        tracks.add(line);
                    }
                    catalog.add(new Music(title, artist, format, year, tracks));
                    break;
                case "VIDEO":
                    title = input.nextLine();
                    format = input.nextLine();
                    year = input.nextInt();
                    int runtime = input.nextInt();
                    catalog.add(new Video(title, format, year, runtime));
                    input.nextLine();
                    break;
                case "END":
                    stop = true;
                    break;
            }
        }

        return catalog;
    }

    /**
     * Main method for the library program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter catalog file name.");
        String fileName = input.nextLine();
        int year;
        int month;
        int day;
        System.out.println("Enter the current year.");
        year = input.nextInt();
        System.out.println("Enter the current month.");
        month = input.nextInt();
        System.out.println("Enter the current day.");
        day = input.nextInt();
        input.nextLine();

        ArrayList<Item> catalog = Library.readCatalog(fileName);

        Library l = new Library(catalog, input, year, month, day);

        l.start();
    }
}
