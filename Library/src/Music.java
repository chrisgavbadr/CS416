import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Music extends Item {

    private String title;
    private String artist;
    private String format;
    private int year;
    private ArrayList<String> tracks;

    /**
     * Constructor for Music object.
     *
     * @param title  [String] Title of music
     * @param artist [String] Artist of music
     * @param format [String] Format of music
     * @param year   [int] Year music was released
     * @param tracks [ArrayList[String]] List of music tracks
     */
    public Music(String title, String artist, String format, int year, ArrayList<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.format = format;
        this.year = year;
        this.tracks = tracks;
    }

    /**
     * Returns basic information about this music object (title, artist, and format).
     *
     * @return [String] String displaying title, artist, and format
     */
    @Override
    public String basicInfo() {
        return String.format("%s by %s (%s)", title, artist, format);
    }

    /**
     * Returns detailed information about this music object (title, artist, format, year of release, and track list).
     *
     * @return [String] String displaying title, artist, format, year of release, and track list
     */
    @Override
    public String detailedInfo() {
        StringBuilder trackList = new StringBuilder("Track list: ");
        for (String track : tracks) {
            trackList.append("\n    ").append(track);
        }
        return String.format("Title: %s\n", title)
                + String.format("Artist: %s\n", artist)
                + String.format("Format: %s\n", format)
                + String.format("Year of release: %d\n", year)
                + trackList;
    }

    /**
     * Checks out music and sets due date 7 days later.
     *
     * @param currDate [LocalDate] Current date
     */
    @Override
    public void checkOut(LocalDate currDate) {
        setCheckedOut();
        setDueDate(currDate.plusDays(7));
    }

    /**
     * Performs search query.
     *
     * @param search [String] Search query
     * @return [boolean] Weather or not search query is contained in title, artist, or track list
     */
    @Override
    public boolean contains(String search) {
        for (String track : tracks) {
            if (track.toLowerCase().contains(search.toLowerCase())) {
                return true;
            }
        }
        return title.toLowerCase().contains(search.toLowerCase())
                || artist.toLowerCase().contains(search.toLowerCase());
    }
}
