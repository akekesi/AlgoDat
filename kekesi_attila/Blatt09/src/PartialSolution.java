import java.util.ArrayList;
import java.util.List;


/**
 * PartialSolution is a class which represents a state of the game
 * from its initial state to its solution. It includes the current
 * state of the board and the move sequence from the initial state
 * to the current state.</br>
 * For the use in the A*-algorithm, the class implements Comparable
 * wrt the cost of the number of moves + heuristic.</br>
 * For the heuristic and game functionality, the respective methods
 * of class {@link Board} are used.
 */
public class PartialSolution implements Comparable<PartialSolution> {
    private Board board;
    private int cost;
    private List<Move> moveSequence;
    private Move lastMove;

    /**
     * Constructor, generates an empty solution based on the provided
     * <em>board</em> with an empty move sequence.
     *
     * @param board initial state of the board
     */
    public PartialSolution(Board board) {
        // TODO 1.2 PartialSolution(board)
        this.board = new Board(board);
        this.cost = 0;
        this.moveSequence = new ArrayList<Move>();
    }

    /**
     * Copy constructor, generates a deep copy of the input
     *
     * @param that The partial solution that is to be copied
     */
    public PartialSolution(PartialSolution that) {
        // TODO 1.2 PartialSolution(PartialSolution)
        this(that.board);
        this.moveSequence.addAll(that.moveSequence);
        this.cost = this.moveSequence.size();
        this.lastMove = that.lastMove;
    }

    /**
     * Performs a move on the board of the partial solution and updates
     * the cost.
     *
     * @param move The move that is to be performed
     */
    public void doMove(Move move) {
        // TODO 1.2 doMove
        this.board.doMove(move);
        this.cost += 1;
        this.moveSequence.add(move);
        this.lastMove = move;
    }

    /**
     * Tests whether the solution has been reached, i.e. whether
     * current board is in the goal state.
     *
     * @return {@code true}, if the board is in goal state
     */
    public boolean isSolution() {
        // TODO 1.2 isSolution
        return this.board.isSolved();
    }

    /**
     * Return the sequence of moves which leads from the initial board
     * to the current state.
     *
     * @return move sequence leading to this state of solution
     */
    public Iterable<Move> moveSequence() {
        // TODO 1.2 moveSequence
        return this.moveSequence;
    }

    /**
     * Generates all possible moves on the current board, <em>except</em>
     * the move which would undo the previous move (if there is one).
     * 
     * @return moves to be considered in the current situation
     */
    public Iterable<Move> validMoves() {
        // TODO 1.2 validMoves
        return this.board.validMoves(this.lastMove);
    }

    /**
     * Compares partial solutions based on their cost.
     * (For performance reasons, the costs should be pre-calculated
     * and stored for each partial solution, rather than computed
     * here each time anew.)
     *
     * @param that the other partial solution
     * @return result of cost comparistion between this and that
     */
    public int compareTo(PartialSolution that) {
        // TODO 1.2 compareTo
        return (this.cost + this.board.manhattan()) - (that.cost + that.board.manhattan());
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Partial solution with moves: \n");
        for (Move move : moveSequence()) {
            msg.append(move).append(", ");
        }
        return msg.substring(0, msg.length() - 2);
    }

    public static void main(String[] args) {
        String filename = "samples/board-3x3-twosteps.txt";
        Board board = new Board(filename);
        PartialSolution partialSolution1 = new PartialSolution(board);
        partialSolution1.doMove(new Move(new Position(1, 2), 0));

        PartialSolution partialSolution2 = new PartialSolution(partialSolution1);
        partialSolution2.doMove(new Move(new Position(2, 2), 3));

        // check deep copy
        System.out.println("board0: " + board.hashCode());
        System.out.println("board1: " + partialSolution1.board.hashCode());
        System.out.println("board2: " + partialSolution2.board.hashCode());
        System.out.println("board0:\n" + board);
        System.out.println("board1:\n" + partialSolution1.board);
        System.out.println("board2:\n" + partialSolution2.board);

        System.out.println("moveSequence1: " + partialSolution1.moveSequence);
        System.out.println("moveSequence2: " + partialSolution2.moveSequence);
        System.out.println("moveSequence1: " + partialSolution1.moveSequence.hashCode());
        System.out.println("moveSequence2: " + partialSolution2.moveSequence.hashCode());

        AStar15Puzzle.printBoardSequence(board, partialSolution2.moveSequence());
    }
}
