import java.util.ArrayList;

/**
 * @author Christian Baduria
 * @version 1.0
 */
public class NimState implements State {

    private int[] stacks;
    private boolean pt;

    /**
     * Main constructor for NimState object.
     *
     * @param n  [int] Number of stacks
     * @param pt [boolean] Whether human player (true) or computer has first turn (false)
     */
    public NimState(int n, boolean pt) {
        stacks = new int[n];
        this.pt = pt;
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = i + 1;
        }
    }

    /**
     * Constructor for copying information from another NimState into a new state.
     *
     * @param stacks [int[]]
     * @param pt     [boolean] Whether human player (true) or computer has first turn (false)
     */
    public NimState(int[] stacks, boolean pt) {
        this.stacks = stacks.clone();
        this.pt = pt;
    }

    /**
     * Returns stacks.
     *
     * @return [int[]] Stacks
     */
    public int[] getStacks() {
        return stacks;
    }

    /**
     * Finds all valid moves from the current state.
     *
     * @return ArrayList of moves.
     */
    @Override
    public ArrayList<State.Move> findAllMoves() {
        ArrayList<State.Move> possibleMoves = new ArrayList<>();
        for (int i = 0; i < stacks.length; i++) {
            for (int j = 1; j <= 3; j++) {
                if (stacks[i] - j >= 0) {
                    possibleMoves.add(new Move(i + 1, j));
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Tests whether the game is over.
     *
     * @return true if game is over, false otherwise.
     */
    @Override
    public boolean gameOver() {
        for (int count : stacks) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the value of an end-game state. Throws an exception if the
     * current state is not an end-game.
     *
     * @return 1 for a win, -1 for a loss.
     */
    @Override
    public int getValue() {
        if (!gameOver()) {
            throw new IllegalStateException("Game is not over.");
        }
        return pt ? -1 : 1;
    }

    /**
     * Returns whether it is the human player's turn or not.
     *
     * @return true if it is the human player's turn.
     */
    @Override
    public boolean isPlayerTurn() {
        return pt;
    }

    /**
     * Tests whether a move is valid and performs it if so.
     *
     * @param move Move The move to be done.
     * @return true if move was valid, false otherwise.
     */
    @Override
    public boolean doMove(State.Move move) {
        NimState.Move nimMove = (NimState.Move) move;
        if (!(1 <= nimMove.stack && nimMove.stack <= stacks.length)
                || !(1 <= nimMove.num && nimMove.num <= 3)
                || stacks[nimMove.stack - 1] - nimMove.num < 0) {
            return false;
        }
        pt = !pt;
        stacks[nimMove.stack - 1] -= nimMove.num;
        return true;
    }

    /**
     * Undoes the effects of the given move.
     *
     * @param move Move The move to be undone.
     */
    @Override
    public void undoMove(State.Move move) {
        NimState.Move nimMove = (NimState.Move) move;
        stacks[nimMove.stack - 1] += nimMove.num;
        pt = !pt;
    }

    /**
     * Returns string representation of NimState.
     *
     * @return [String] String representation of NimState
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < stacks.length; i++) {
            string.append(i + 1).append(": ");
            for (int j = 0; j < stacks[i]; j++) {
                string.append("X");
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * @author Christian Baduria
     * @version 1.0
     */
    public static class Move implements State.Move {

        int stack;
        int num;

        /**
         * Constructor for Move object.
         *
         * @param stack [int] Integer indicating which stack to take from
         * @param num   [int] Number of items to take from stack
         */
        public Move(int stack, int num) {
            this.stack = stack;
            this.num = num;
        }

        /**
         * Determines equality between other object and this object.
         *
         * @param o [Object] Other object to be compared with this object
         * @return [boolean] Whether other object is identical to this object
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Move move = (Move) o;
            return stack == move.stack && num == move.num;
        }

        /**
         * Returns string representation of move to be made.
         *
         * @return [String] String describing move to be made
         */
        public String toString() {
            return String.format("Taking %d from stack %d", num, stack);
        }
    }
}
