import java.util.LinkedList;

/**
 * The {@code Position} class stores x- and y-coordinate of a cell in a 9x9 <b>Sudoku</b> grid.
 * It provides methods {@code inRow()}, {@code inCol()}, and {@code inSquare()} to return the
 * <i>other</i> positions in the same row, column or 3x3 square.
 *
 * @author Benjamin Blankertz (CC-BY-SA 2023)
 */
public class Position {
    protected int x;
    protected int y;

    /**
     * constructor for a {@code Position} object given x- and y-coordinate
     * @param x the x-coordinate of a position in a Sudoku field
     * @param y the y-coordinate of a position in a Sudoku field
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * creates a list of all other positions in the row of {@code this} position
     * @return a {@link LinkedList} of the positions in this row
     */
    public Iterable<Position> affectedPositions()
    {
        LinkedList<Position> affectedPositions = new LinkedList<>();
        // other Positions in the same row
        for (int x = 0; x < Board.Nsize; x++) {
            if (x != this.x)
                affectedPositions.add(new Position(x, this.y));
        }
        // other Positions in the same column
        for (int y = 0; y < Board.Nsize; y++) {
            if (y != this.y)
                affectedPositions.add(new Position(this.x, y));
        }
        // other Positions in the same subsquare
        int x0 = (this.x / Board.Nsqrt) * Board.Nsqrt;
        int y0 = (this.y / Board.Nsqrt) * Board.Nsqrt;
        for (int x = x0; x < x0+Board.Nsqrt; x++) {
            for (int y = y0; y < y0+Board.Nsqrt; y++) {
                if (x != this.x && y != this.y)
                    affectedPositions.add(new Position(x, y));
            }
        }
        return affectedPositions;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
