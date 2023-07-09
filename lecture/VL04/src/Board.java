import java.util.*;

/**
 * The {@code Board} class stores the state of a 9x9 <b>Sudoku</b> grid.
 * It provides functionality for Sudoku solvers. A <i>Priority Queue</i> can store
 * a candidate list for each cell. It employs the class {@link CandidateList}, which implements
 * the {@link Comparable} interface based on the length of the candidate list, providing fast
 * access of the cell with the smallest number of possible candidates.
 * <p>
 * A <b>Sudoku Solver</b> based on <i>backtracking</i> is provided in the class {@link SudokuSolver}.
 * </p>
 *
 * @author Benjamin Blankertz (CC-BY-SA 2023)
 */
public class Board {
    /**
     * the array representing the <b>Sudoku</b> grid, storing the contents of all cells
     */
    private int[][] field;
    /**
     * the {@link LinkedList} which stores a {@link CandidateList} for each free cell
     */
    protected PriorityQueue<CandidateList> candidates = new PriorityQueue<>();

    /**
     * a {@link LinkedList} of all positions of a <b>Sudoku</b> grid
     */
    protected static LinkedList<Position> positions;
    /**
     * the constant used to indicate that a cell is free
     */
    public static final int FREE = 0;
    /**
     * the constant specifying the size (side length) of the grid
     */
    public static final int Nsize = 9;
    /**
     * the constant specifying the square root of the size, i.e., the length of the subsquares
     */
    public static final int Nsqrt = 3;

    /**
     * Constructor which takes a {@link Scanner} as input, e.g., related to a file.
     * The file specifies the clues (given digits) and free cells (0), separated by spaces.
     * Each row may be in a separate line.
     *
     * @param in Scanner object, e.g., set from a {@code FileInputStream}
     */
    public Board(Scanner in) {
        positions = new LinkedList<>();
        for (int y = 0; y < Nsize; y++) {
            for (int x = 0; x < Nsize; x++) {
                positions.add(new Position(x, y));
            }
        }
        field = new int[Nsize][Nsize];
        for (Position pos : positions) {
            field[pos.y][pos.x] = in.nextInt();
        }
        populateCandidates();
    }

    /**
     * Copy constructor which copies the values of all cells of a sudoku grid.
     * Note, that the list of {@code candidates} is <em>not</em> copied.
     *
     * @param that the board to be copied
     */
    public Board(Board that) {
        field = new int[Nsize][Nsize];
        for (Position pos : positions) {
            field[pos.y][pos.x] = that.getField(pos);
        }
        populateCandidates();
    }

    /**
     * Retrieve the value (digit) of a given {@code cell} of a sudoku grid.
     *
     * @param cell in the sudoku grid
     * @return the value (digit) of the given cell
     * @throws IndexOutOfBoundsException if {@code cell} coordinates are not in 0...8.
     */
    protected int getField(Position cell) {
        if (cell.x < 0 || cell.x >= Nsize || cell.y < 0 || cell.y >= Nsize) {
            throw new IndexOutOfBoundsException();
        }
        return field[cell.y][cell.x];
    }

    /**
     * Store a given value (digit) in a given cell of the sudoku grid
     *
     * @param cell   in the sudoku grid
     * @param number to be filled in
     * @throws IndexOutOfBoundsException if {@code cell} coordinates are not in 0...8.
     * @throws InputMismatchException    if the value is not in 1...9
     */
    protected void setField(Position cell, int number) {
        if (cell.x < 0 || cell.x >= Nsize || cell.y < 0 || cell.y >= Nsize) {
            throw new IndexOutOfBoundsException();
        } else if (number < 0 || number > Nsize) {
            throw new InputMismatchException();
        } else {
            field[cell.y][cell.x] = number;
        }
    }

    /**
     * Fill in a given {@code value} (digit) in a {@code cell} of the sudoku grid and update the list of candidates.
     *
     * @param cell  in the sudoku grid
     * @param value to be filled in
     */
    public void fillCell(Position cell, int value) {
        setField(cell, value);
        populateCandidates();
    }

    /**
     * Clear a {@code cell} of the sudoku grid from the stored value and update the list of candidate
     *
     * @param cell in the sudoku grid
     */
    public void freeCell(Position cell) {
        setField(cell, FREE);
        populateCandidates();
    }

    /**
     * Populate the list of {@code candidates} for all empty cells with all possible values that
     * would fit into that cell.
     */
    public void populateCandidates() {
        candidates.clear();
        for (Position cell : positions) {
            if (getField(cell) == FREE) {
                int[] cellCandidates = new int[Nsize];
                for (Position pos : cell.affectedPositions()) {
                    int value = getField(pos);
                    if (value != FREE) {
                        cellCandidates[value - 1] = 1;
                    }
                }
                CandidateList possibleValues = new CandidateList(cell);
                for (int val = 0; val < Nsize; val++) {
                    if (cellCandidates[val] == 0)
                        possibleValues.add(val + 1);
                }
                candidates.add(possibleValues);
            }
        }
    }

    public void print() {
        for (Position pos : positions) {
            if (getField(pos) == FREE)
                System.out.print(".");
            else
                System.out.print(getField(pos));
            System.out.print(" ");
            if (pos.x == Nsize - 1)
                System.out.println();
        }
    }
}
