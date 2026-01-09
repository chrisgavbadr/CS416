import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Christian Baduria
 * @version 1.0
 */

public class Sudoku {
    private static int recursionCount;
    private static int backupCount;
    private Board board;

    /**
     * Constructor for Sudoku game.
     *
     * @param sc [Scanner] Scanner object for reading initial board layout
     */
    public Sudoku(Scanner sc) {
        board = new Board(sc);
        recursionCount = 0;
        backupCount = 0;
    }

    /**
     * Initiates recursive function for solving sudoku board.
     *
     * @return [boolean] Whether or not the sudoku board is solvable
     */
    public boolean solve() {
        return solve(new Location(0, 0));
    }

    /**
     * Recursively solves sudoku board.
     *
     * @param loc [Location] Location on sudoku board
     * @return [boolean] Whether or not the sudoku board is solvable
     */
    public boolean solve(Location loc) {
        recursionCount++;
        if (loc == null) {
            return true;
        } else if (board.get(loc.getRow(), loc.getColumn()) != 0) {
            if (solve(loc.next())) {
                return true;
            } else if (loc.equals(new Location(0, 0))) {
                backupCount++;
                return false;
            }
            return false;
        }
        for (int i = 1; i <= 9; i++) {
            if (board.isAllowed(loc.getRow(), loc.getColumn(), i)) {
                board.set(loc.getRow(), loc.getColumn(), i);
                if (solve(loc.next())) {
                    return true;
                } else {
                    backupCount++;
                    board.set(loc.getRow(), loc.getColumn(), 0);
                }
            }
        }
        if (loc.equals(new Location(0, 0))) {
            backupCount++;
        }
        return false;
    }

    /**
     * Returns sudoku board object.
     *
     * @return [Board] Sudoku board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns recursion count.
     *
     * @return [int] Recursion count
     */
    public static int getRecursionCount() {
        return recursionCount;
    }

    /**
     * Returns backup count.
     *
     * @return [int] Backup count
     */
    public static int getBackupCount() {
        return backupCount;
    }

    /**
     * Main function that controls entire sudoku setup and solving.
     *
     * @param args [String[]] args parameter of main function
     * @throws FileNotFoundException Thrown if text file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path to the sudoku file:");
        String fileName = sc.next();
        File file = new File(fileName);
        Scanner boardScanner = new Scanner(file);
        Sudoku game = new Sudoku(boardScanner);
        System.out.println("Initial configuration of the sudoku");
        System.out.println(game.getBoard().toString());
        boolean solvable = game.solve();
        if (solvable) {
            System.out.println("Successful!");
            System.out.println("Final configuration of the sudoku");
            System.out.println(game.getBoard().toString());
            System.out.println("Recursion count = " + recursionCount);
            System.out.println("Backup count = " + backupCount);
        } else {
            System.out.println("Sudoku unsolvable from this configuration");
        }
    }
}
