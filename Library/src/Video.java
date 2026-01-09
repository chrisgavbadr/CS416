import java.time.LocalDate;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Video extends Item {

    private String title;
    private String format;
    private int year;
    private int runtime;

    /**
     * Constructor for Video object.
     *
     * @param title   [String] Title of video
     * @param format  [String] Format of video
     * @param year    [int] Year video was released
     * @param runtime [int] Runtime of video in minutes
     */
    public Video(String title, String format, int year, int runtime) {
        this.title = title;
        this.format = format;
        this.year = year;
        this.runtime = runtime;
    }

    /**
     * Returns basic information about this music object (title and format).
     *
     * @return [String] String displaying title and format of video
     */
    @Override
    public String basicInfo() {
        return String.format("%s (%s)", title, format);
    }

    /**
     * Returns detailed information about this music object (title, format, year of release, and runtime).
     *
     * @return [String] String displaying title, format, year of release, and runtime of video
     */
    @Override
    public String detailedInfo() {
        return String.format("Title: %s\n", title)
                + String.format("Format: %s\n", format)
                + String.format("Year of release: %d\n", year)
                + String.format("Runtime (minutes): %d", runtime);
    }

    /**
     * Checks out video and sets due date 3 days later.
     *
     * @param currDate [LocalDate] Current date
     */
    @Override
    public void checkOut(LocalDate currDate) {
        setCheckedOut();
        setDueDate(currDate.plusDays(3));
    }

    /**
     * Performs search query.
     *
     * @param search [String] Search query
     * @return [boolean] Whether or not search query is contained in title
     */
    @Override
    public boolean contains(String search) {
        return title.toLowerCase().contains(search.toLowerCase());
    }
}
