import java.util.ArrayList;

public class TicTacToeState implements State {

    final static int BOARD_SIZE = 3;
    String[][] board;
    boolean playerTurn;

    /**
     * Default constructor. Creates a starting game state.
     * Computer will be X, and player will be O.
     *
     * @param turn Indicates whether it is the player's turn first.
     */
    public TicTacToeState(boolean turn) {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        this.playerTurn = turn;
    }


    /**
     * Copy constructor. Creates a new state by
     * copying the values in the board and turn parameters.
     * Computer will be X, and player will be O.
     *
     * @param board The current game board to be copied.
     * @param turn  Indicates whether it is the player's turn in this state.
     */
    public TicTacToeState(String[][] board, boolean turn) {
        this.board = new String[BOARD_SIZE][BOARD_SIZE];

        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                this.board[r][c] = board[r][c];
            }
        }

        this.playerTurn = turn;
    }

    /**
     * Returns the mark for the player whose turn it is in this state.
     *
     * @return "O" if playerTurn is true, "X" otherwise.
     */
    public String getMark() {
        return playerTurn ? "O" : "X";
    }

    /**
     * Returns the board for this state.
     *
     * @return The board.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Returns whether it is the human player's turn or not.
     *
     * @return true if it is the human player's turn. (The current turn is "O".)
     */
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    /**
     * Returns a string representation of this state.
     *
     * @return The string representing this state.
     */
    public String toString() {
        String ret = "";
        String bar = " -------------\n";
        ret += bar;

        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (board[r][c] == null) {
                    ret += " |  ";
                } else {
                    ret += " | " + board[r][c];
                }
            }
            ret += " |\n";
            ret += bar;
        }

        return ret;
    }

    /**
     * Finds all legal moves from the current state.
     *
     * @return ArrayList of moves.
     */
    public ArrayList<State.Move> findAllMoves() {
        ArrayList<State.Move> validMoves = new ArrayList<>();
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (board[r][c] == null) {
                    validMoves.add(new Move(r, c));
                }
            }
        }
        return validMoves;
    }

    /**
     * Tests whether the game is over.
     *
     * @return true if game is over, false otherwise.
     */
    public boolean gameOver() {
        if (playerWins()) {
            return true;
        }
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (board[r][c] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the value of an end-game state. Throws a new IllegalStateException if the
     * current state is not an end-game.
     *
     * @return 1 for a win for X, -1 for a loss.
     */
    public int getValue() {
        if (!gameOver()) {
            throw new IllegalStateException("Currently not in end-game state.");
        }
        if (playerXWins()) {
            return 1;
        } else if (playerOWins()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Tests whether a move is legal and performs it if so.
     *
     * @param m Move The move to be done.
     * @return true if move was legal, false otherwise.
     */
    public boolean doMove(State.Move m) {
        TicTacToeState.Move move = (TicTacToeState.Move) m;
        if ((0 <= move.r && move.r < BOARD_SIZE)
                && (0 <= move.c && move.c < BOARD_SIZE)
                && board[move.r][move.c] == null) {
            playerTurn = !playerTurn;
            if (playerTurn) {
                board[move.r][move.c] = "X";
            } else {
                board[move.r][move.c] = "O";
            }
            return true;
        }
        return false;
    }

    /**
     * Undoes the effects of the given move.
     *
     * @param m Move The move to be undone.
     */
    public void undoMove(State.Move m) {
        TicTacToeState.Move move = (TicTacToeState.Move) m;
        board[move.r][move.c] = null;
    }

    private boolean playerWins() {
        return playerXWins() || playerOWins();
    }

    private boolean playerXWins() {
        return rowWins("X") || columnWins("X") || diagonalWins("X");
    }

    private boolean playerOWins() {
        return rowWins("O") || columnWins("O") || diagonalWins("O");
    }

    private boolean rowWins(String player) {
        boolean winningState;
        for (int c = 0; c < BOARD_SIZE; c++) {
            winningState = true;
            for (int r = 0; r < BOARD_SIZE - 1; r++) {
                if (board[r][c] == null || board[r + 1][c] == null
                        || !board[r][c].equals(player) || !board[r + 1][c].equals(player)) {
                    winningState = false;
                    break;
                }
            }
            if (winningState) {
                return true;
            }
            winningState = true;
        }
        return false;
    }

    private boolean columnWins(String player) {
        boolean winningState;
        for (int r = 0; r < BOARD_SIZE; r++) {
            winningState = true;
            for (int c = 0; c < BOARD_SIZE - 1; c++) {
                if (board[r][c] == null || board[r][c + 1] == null
                        || !board[r][c].equals(player) || !board[r][c + 1].equals(player)) {
                    winningState = false;
                    break;
                }
            }
            if (winningState) {
                return true;
            }
            winningState = true;
        }
        return false;
    }

    private boolean diagonalWins(String player) {
        boolean winningState = true;
        for (int r = 0, c = 0; r < BOARD_SIZE - 1; r++, c++) {
            if (board[r][c] == null || board[r + 1][c + 1] == null
                    || !board[r][c].equals(player) || !board[r + 1][c + 1].equals(player)) {
                winningState = false;
                break;
            }
        }
        if (winningState) {
            return true;
        }
        winningState = true;
        for (int r = board.length - 1, c = 0; c < BOARD_SIZE - 1; r--, c++) {
            if (board[r][c] == null || board[r - 1][c + 1] == null
                    || !board[r][c].equals(player) || !board[r - 1][c + 1].equals(player)) {
                winningState = false;
                break;
            }
        }
        return winningState;
    }

    public class Move implements State.Move {

        int r;
        int c;

        /**
         * Default constructor.
         */
        public Move(int r, int c) {
            this.r = r;
            this.c = c;
        }

        /**
         * Returns a string representation of this move.
         *
         * @return The string representing this move.
         */
        public String toString() {
            return "row " + r + " column " + c;
        }

        /**
         * Determine whether this move is equal to another object.
         *
         * @return true if all data from the move matches, false otherwise.
         */
        public boolean equals(Object o) {
            if (o instanceof Move) {
                Move m = (Move) o;

                return m.r == r && m.c == c;
            }

            return false;
        }

    }
}
