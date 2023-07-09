import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    public LinkedList<Position> PositionsInRow(int row) {
        LinkedList<Position> positionsInRow = new LinkedList<>();
        for (int x = 0; x < Board.Nsize; x++) {
            positionsInRow.add(new Position(x, row));
        }
        return positionsInRow;
    }

    public LinkedList<Position> PositionsInColumn(int col) {
        LinkedList<Position> positionsInCol = new LinkedList<>();
        for (int y = 0; y < Board.Nsize; y++) {
            positionsInCol.add(new Position(col, y));
        }
        return positionsInCol;
    }

    public LinkedList<Position> PositionsInSquare(int xsq, int ysq) {
        LinkedList<Position> positionsInSquare = new LinkedList<>();
        int x0 = xsq * Board.Nsqrt;
        int y0 = ysq * Board.Nsqrt;
        for (int x = x0; x < x0 + Board.Nsqrt; x++) {
            for (int y = y0; y < y0 + Board.Nsqrt; y++) {
                positionsInSquare.add(new Position(x, y));
            }
        }
        return positionsInSquare;
    }

    void isComplete(Board board) {
        for (Position cell : Board.positions) {
            assertNotEquals(Board.FREE, board.getField(cell), "Not all cells are filled.");
        }
    }

    void testUniquenessInRows(Board board) {
        int[] fullCandidates = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int y = 0; y < Board.Nsize; y++) {
            int[] cellCandidates = Arrays.copyOf(fullCandidates, Board.Nsize);
            for (Position pos : PositionsInRow(y)) {
                int value = board.getField(pos);
                assertEquals(1, cellCandidates[value - 1], "Value " + value + " occurs multiple times in row " + y + ".");
                cellCandidates[value - 1] = 0;
            }
        }
    }

    void testUniquenessInColumns(Board board) {
        int[] fullCandidates = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int x = 0; x < Board.Nsize; x++) {
            int[] cellCandidates = Arrays.copyOf(fullCandidates, Board.Nsize);
            for (Position pos : PositionsInColumn(x)) {
                int value = board.getField(pos);
                assertEquals(1, cellCandidates[value - 1], "Value " + value + " occurs multiple times in column " + x + ".");
                cellCandidates[value - 1] = 0;
            }
        }
    }

    void testUniquenessInSquares(Board board) {
        int[] fullCandidates = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int x = 0; x < Board.Nsqrt; x++) {
            for (int y = 0; y < Board.Nsqrt; y++) {
                int[] cellCandidates = Arrays.copyOf(fullCandidates, Board.Nsize);
                for (Position pos : PositionsInSquare(x, y)) {
                    int value = board.getField(pos);
                    assertEquals(1, cellCandidates[value - 1], "Value " + value + " occurs multiple times in square (" + x + ", " + y + ").");
                    cellCandidates[value - 1] = 0;
                }
            }
        }
    }

    @Test
    void backtracking() throws FileNotFoundException {
        System.setIn(new FileInputStream("samples/sudoku_no0.txt"));
//        System.setIn(new FileInputStream("samples/sudoku_skiena_hard.txt"));
        Board board = new Board(new Scanner(System.in));
        SudokuSolver sudoku = new SudokuSolver(board);
        sudoku.backtracking();
        System.out.println("Solution returned by backtracking():");
        sudoku.board.print();
        isComplete(sudoku.board);
        testUniquenessInRows(sudoku.board);
        testUniquenessInColumns(sudoku.board);
        testUniquenessInSquares(sudoku.board);
    }
}