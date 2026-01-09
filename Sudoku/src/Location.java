/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Location {
    private int row;
    private int column;

    /**
     * Constructor for Location object.
     *
     * @param r [int] Row
     * @param c [int] Column
     */
    public Location(int r, int c) {
        this.row = r;
        this.column = c;
    }

    /**
     * Returns row component.
     *
     * @return [int] Row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns column component.
     *
     * @return [int] Column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns next location on sudoku board.
     *
     * @return [Location] Next location on sudoku board
     */
    public Location next() {
        if (column < 8) {
            return new Location(row, column + 1);
        } else if (row < 8) {
            return new Location(row + 1, 0);
        } else {
            return null;
        }
    }

    /**
     * Checks equality between this object and other object.
     *
     * @param o [Object] Object to be compared with
     * @return [boolean] If this object is equal to other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return row == location.row && column == location.column;
    }

    /**
     * Expresses Location object as string.
     *
     * @return [Location] Location object as string
     */
    @Override
    public String toString() {
        return String.format("%d, %d", row, column);
    }
}
