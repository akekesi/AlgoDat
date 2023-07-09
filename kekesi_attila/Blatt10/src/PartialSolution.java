import java.util.LinkedList;


/**
 * PartialSolution provides at least the functionality which is required
 * for the use in searching for solutions of the game in a search tree.
 * It can store a game situation (Board) and a sequence of moves.
 */
public class PartialSolution {
    // TODO
    private Board board;
    private LinkedList<Move> moveSequence;

    /**
     * Constructor, generates an empty solution based on the provided
     * <em>board</em> with an empty move sequence.
     *
     * @param board initial state of the board
     */
    public PartialSolution(Board board) {
        // TODO
        this.board = new Board(board);
        this.moveSequence = new LinkedList<>();
    }

    /**
     * Copy constructor, generates a deep copy of the input
     *
     * @param that The partial solution that is to be copied
     */
    public PartialSolution(PartialSolution that) {
        // TODO
        this(that.board);
        this.moveSequence.addAll(that.moveSequence);
    }

    /**
     * Getter of board.
     *
     * @return this.board
     */
    public Board getBoard() {
        // TODO
        return this.board;
    }

    /**
     * Performs a move on the board of the partial solution.
     *
     * @param move The move that is to be performed
     */
    public void doMove(Move move) {
        // TODO
        this.board.doMove(move);
        this.moveSequence.add(move);
    }

    /**
     * Tests whether the solution has been reached, i.e. whether
     * current board is in the goal state.
     *
     * @return {@code true}, if the board is in goal state
     */
    public boolean isSolution() {
        // TODO
        return this.board.targetReached();
    }

    /**
     * Return the sequence of moves which resulted in this partial solution.
     *
     * @return The sequence of moves.
     */
    public LinkedList<Move> moveSequence() {
        // TODO
        return this.moveSequence;
    }

    /**
     * Generates all possible moves on the current board.
     *
     * @return moves to be considered in the current situation
     */
    public Iterable<Move> validMoves() {
        // TODO
        return this.board.validMoves();
    }

    @Override
    public boolean equals(Object o) {
        // TODO
        if (o == this) {
            return true;
        }
        if (!(o instanceof PartialSolution)) {
            return false;
        }
        PartialSolution partialSolution = (PartialSolution) o;
        return this.board.equals(partialSolution.getBoard());
    }

    @Override
    public int hashCode() {
        // TODO
        return this.board.hashCode();
    }

    @Override
    public String toString() {
        String str = "";
        int lastRobot = -1;
        for (Move move : moveSequence()) {
            if (lastRobot == move.iRobot) {
                str += " -> " + move.endPosition;
            } else {
                if (lastRobot != -1) {
                    str += ", ";
                }
                str += "R" + move.iRobot + " -> " + move.endPosition;
            }
            lastRobot = move.iRobot;
        }
        return str;
    }
}
