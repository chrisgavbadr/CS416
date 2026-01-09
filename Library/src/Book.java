import java.time.LocalDate;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Book extends Item {

    private String title;
    private String author;
    private int pages;
    private int year;

    /**
     * Constructor for Book object.
     *
     * @param title  [String] Title of book
     * @param author [String] Author of book
     * @param pages  [int] Number of pages in book
     * @param year   [int] Year book was released
     */
    public Book(String title, String author, int pages, int year) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    /**
     * Returns basic information about this music object (title and author).
     *
     * @return [String] String displaying title and author of book
     */
    @Override
    public String basicInfo() {
        return String.format("%s, by %s", title, author);
    }

    /**
     * Returns detailed information about this music object (title, author, number of pages, and year of release).
     *
     * @return [String] String displaying title, author, number of pages, and year of release of book
     */
    @Override
    public String detailedInfo() {
        return String.format("Title: %s\n", title)
                + String.format("Author: %s\n", author)
                + String.format("Pages: %d\n", pages)
                + String.format("Publication year: %d", year);
    }

    /**
     * Checks out book and sets due date 14 days later.
     *
     * @param currDate [LocalDate] Current date
     */
    @Override
    public void checkOut(LocalDate currDate) {
        setCheckedOut();
        setDueDate(currDate.plusDays(14));
    }

    /**
     * Performs search query.
     *
     * @param search [String] Search query
     * @return [boolean] Whether or not search query is contained in title or author
     */
    @Override
    public boolean contains(String search) {
        return title.toLowerCase().contains(search.toLowerCase())
                || author.toLowerCase().contains(search.toLowerCase());
    }
}
