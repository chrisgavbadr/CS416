import java.util.Scanner;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Board {
    private int[][] board;

    /**
     * Constructor for Board object.
     *
     * @param sc [Scanner] Scanner object for reading initial board layout
     */
    public Board(Scanner sc) {
        board = readBoard(sc);
    }

    /**
     * Reads Scanner input and create 9x9 array of integers representing sudoku board.
     *
     * @param sc [Scanner] Scanner object for reading initial board layout
     * @return [int[]] 9x9 array of integers representing sudoku board
     */
    public static int[][] readBoard(Scanner sc) {
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            char[] row = sc.nextLine().toCharArray();
            if (row.length != 9) {
                return null;
            }
            for (int j = 0; j < 9; j++) {
                char c = row[j];
                if ('1' <= c && c <= '9') {
                    board[i][j] = Character.getNumericValue(c);
                } else if (c == '-') {
                    board[i][j] = 0;
                } else {
                    return null;
                }
            }
        }
        return board;
    }

    /**
     * Gets value of given location from sudoku board.
     *
     * @param row [int] Row value
     * @param col [int] Column value
     * @return [int] Value at given location
     */
    public int get(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets value of given location on sudoku board.
     *
     * @param row   [int] Row value
     * @param col   [int] Column value
     * @param value [int] Value to set at given location
     */
    public void set(int row, int col, int value) {
        board[row][col] = value;
    }

    /**
     * Checks whether or not number is contained in row.
     *
     * @param row    [int] Row to be checked
     * @param number [int] Value to check presence of
     * @return [boolean] Whether or not number is contained in row
     */
    public boolean containsInRow(int row, int number) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not number is contained in column.
     *
     * @param col    [int] Column to be checked
     * @param number [int] Value to check presence of
     * @return [boolean] Whether or not number is contained in column
     */
    public boolean containsInCol(int col, int number) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not number is contained in 3x3 box.
     *
     * @param row    [int] Row
     * @param col    [int] Column
     * @param number [int] Value to check presence of
     * @return [boolean] Whether or not number is contained in column
     */
    public boolean containsInBox(int row, int col, int number) {
        int rowPivot = 3 * (row / 3);
        int colPivot = 3 * (col / 3);
        for (int i = rowPivot; i < rowPivot + 3; i++) {
            for (int j = colPivot; j < colPivot + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether or not number is allowed in given location.
     *
     * @param row    [int] Row
     * @param col    [int] Column
     * @param number [int] Value to be written in location
     * @return [boolean] Whether or not number is allowed in given location
     */
    public boolean isAllowed(int row, int col, int number) {
        return board[row][col] == 0
                && !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }

    /**
     * Expresses board object as string.
     *
     * @return [String] Board expressed as string
     */
    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0) {
                boardString.append("+-------+-------+-------+\n");
            }
            for (int j = 0; j < board[i].length; j++) {
                if (j % 3 == 0) {
                    boardString.append("| ");
                }
                if (board[i][j] == 0) {
                    boardString.append("- ");
                } else {
                    boardString.append(board[i][j]).append(" ");
                }
            }
            boardString.append("|\n");
        }
        boardString.append("+-------+-------+-------+");
        return boardString.toString();
    }
}
