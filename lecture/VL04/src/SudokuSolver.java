import java.io.FileInputStream;
import java.util.Scanner;

/**
 * The class {@code SudokuSolver} provides a method {@code backtracking} for solving
 * <b>Sudoku</b> grids.
 *
 * @author Benjamin Blankertz (CC-BY-SA 2023)
 */
public class SudokuSolver {

    protected Board board;

    public SudokuSolver(Board startingBoard) {
        board = new Board(startingBoard);
    }

    public boolean backtracking() {
        if (board.candidates.isEmpty())
            return true;
        CandidateList candy = board.candidates.poll();
        for (int value : candy) {
            board.fillCell(candy.cell(), value);
            if (backtracking()) {
                return true;
            }
            board.freeCell(candy.cell());
        }
        return false;
    }

    public static void main(String[] args) throws java.io.FileNotFoundException {
        // generated with https://www.surfpoeten.de/apps/sudoku/generator
//        System.setIn(new FileInputStream("samples/sudoku_sp_moderate00.txt"));
        System.setIn(new FileInputStream("samples/sudoku_sp_extremehard00.txt"));
        // taken from Skiena S., The Algorithm Design Manual.
//        System.setIn(new FileInputStream("samples/sudoku_skiena_hard.txt"));
        Board board = new Board(new Scanner(System.in));
        board.print();
        SudokuSolver sudoku = new SudokuSolver(board);
        long start = System.nanoTime();
        sudoku.backtracking();
        long duration = (System.nanoTime() - start) / 1000;
        System.out.println("Solution:");
        sudoku.board.print();
        System.out.println("Computing time: " + duration / 1000 + " ms");
    }
}
