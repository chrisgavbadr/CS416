import java.util.ArrayList;

/**
 * @author Christian Baduria
 * @version 1.0
 */
public class GameTree {

    private Node root;
    private int size;

    /**
     * Constructor for GameTree object.
     *
     * @param start [State] Starting state
     */
    public GameTree(State start) {
        size = 0;
        root = buildTree(start, null);
    }

    /**
     * Returns root of game tree.
     *
     * @return [Node] Root of game tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Makes subtree of all possible moves from current state.
     *
     * @param state [State] Current board state
     * @param move [State.Move] Move to be made on board state
     * @return [Node] Subtree of all possible moves
     */
    public Node buildTree(State state, State.Move move) {
        Node subtree = new Node(move);
        size++;
        if (state.gameOver()) {
            subtree.value = state.getValue();
        } else {
            ArrayList<State.Move> possibleMoves = state.findAllMoves();
            for (State.Move m : possibleMoves) {
                state.doMove(m);
                subtree.children.add(buildTree(state, m));
                state.undoMove(m);
            }
            if (!state.isPlayerTurn()) {
                subtree.bestChild = subtree.children.get(0);
                int max = subtree.children.get(0).getValue();
                for (Node n : subtree.children) {
                    if (n.value > max) {
                        max = n.value;
                        subtree.bestChild = n;
                    }
                }
                subtree.value = max;
            } else {
                subtree.bestChild = subtree.children.get(0);
                int min = subtree.children.get(0).getValue();
                for (Node n : subtree.children) {
                    if (n.value < min) {
                        min = n.value;
                        subtree.bestChild = n;
                    }
                }
                subtree.value = min;
            }
        }
        return subtree;
    }

    /**
     * Returns size of game tree.
     *
     * @return [int] Size of game tree
     */
    public int getSize() {
        return size;
    }

    /**
     * @author Christian Baduria
     * @version 1.0
     */
    public class Node {

        State.Move move;
        int value;
        Node bestChild;
        ArrayList<Node> children;

        /**
         * Constructor for game tree node.
         *
         * @param m [State.Move] Move to be made
         */
        public Node(State.Move m) {
            move = m;
            value = 0;
            bestChild = null;
            children = new ArrayList<>();
        }

        /**
         * Returns value.
         *
         * @return [int] -1 if human player wins, 1 if computer player wins, or else 0
         */
        public int getValue() {
            return value;
        }

        /**
         * Returns move that was made.
         *
         * @return [State.Move] Move that was made
         */
        public State.Move getMove() {
            return move;
        }

        /**
         * Returns node of best child.
         *
         * @return [Node] Node of best child
         */
        public Node getBestChild() {
            return bestChild;
        }

        /**
         * Finds child with associated move.
         *
         * @param m [State.Move] Move used to find child
         * @return [Node] Child with associated move
         */
        public Node findChild(State.Move m) {
            for (Node child : children) {
                if (child.move.equals(m)) {
                    return child;
                }
            }
            return null;
        }

        /**
         * Returns projected winner.
         *
         * @return [String] Projected winner
         */
        public String getPrediction() {
            if (value == 1) {
                return "computer";
            } else if (value == -1) {
                return "player";
            } else {
                return "no one";
            }
        }
    }
}
