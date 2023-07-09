import java.util.Stack;
import static java.lang.Math.abs;
import java.util.InputMismatchException;


/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    private  int[][] board;
    private int[] tokens = {-1, 0, 1};
    
    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        // TODO
        this.n = n;
        if (n < 1 || 10 < n) {
            throw new InputMismatchException("n is not valid");
        }
        this.board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.board[i][j] = 0;
            }
        }
    }
    
    /**
     *  @return     length/width of the Board object
     */
    public int getN() {
        return n;
    }

    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        // TODO
        int nFreeFields = 0;
        for (int[] row : this.board) {
            for (int field : row) {
                if (field == 0) {
                    nFreeFields++;
                }
            }
        }
        return nFreeFields;
    }

    /**
     *  @return     result of position validation
     */
    public boolean isPositionValid(Position position) {
        if (0 <= position.x && position.x < this.n && 0 <= position.y && position.y < this.n) {
            return true;
        }
        return false;
    }

    /**
     *  @return     result of token validation
     */
    public boolean isTokenValid(int token) {
        for (int tokenValid : this.tokens) {
            if (token == tokenValid) {
                return true;
            }
        }
        return false;
    }

    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        // TODO
        if (!this.isPositionValid(pos)) {
            throw new InputMismatchException("Position is not valid");
        }
        return this.board[pos.y][pos.x];
    }

    /**
     *  Sets the specified token at Position pos.
     */
    public void setField(Position pos, int token) throws InputMismatchException
    {
        // TODO
        if (!this.isTokenValid(token)) {
            throw new InputMismatchException("Token is not valid");
        }
        if (!this.isPositionValid(pos)) {
            throw new InputMismatchException("Position is not valid");
        }
        this.board[pos.y][pos.x] = token;
    }
    
    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        // TODO
        setField(pos, player);
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        // TODO
        setField(pos, 0);
    }
    
    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon() {
        // TODO
        int sumRow;
        int sumColumn;
        int sumDiagonal1;
        int sumDiagonal2;
        sumDiagonal1 = 0;
        sumDiagonal2 = 0;
        for (int i = 0; i < this.n; i++) {
            sumRow = 0;
            sumColumn = 0;
            for (int j = 0; j < this.n; j++) {
                sumRow += this.board[i][j];
                sumColumn += this.board[j][i];
            }
            if (abs(sumRow) == this.n || abs(sumColumn) == this.n) {
                return true;
            }
            sumDiagonal1 += this.board[i][i];
            sumDiagonal2 += this.board[i][this.n - 1 - i];
        }
        if (abs(sumDiagonal1) == this.n || abs(sumDiagonal2) == this.n) {
            return true;
        }
        return false;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        // TODO
        Stack<Position> validMoves = new Stack<>();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.board[i][j] == 0) {
                    validMoves.push(new Position(j, i));
                }
            }
        }
        return validMoves;
    }

    /**
     *  @return     convertet token as string
     */
    public String convertToken(int token) {
        if (token == 1) {
            return "x";
        }
        if (token == -1) {
            return "o";
        }
        return " ";
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        // TODO
        String printRow;
        for (int i = 0; i < this.n; i++) {
            printRow = "";
            for (int j = 0; j < this.n; j++) {
                printRow += this.convertToken(this.board[i][j]);
                if (j < this.n - 1) {
                    printRow += " | ";
                }
            }
            System.out.println(printRow);
            printRow = "";
            if (i < this.n - 1) {
                for (int k = 0; k < 4 * this.n - 3; k++) {
                    printRow += "-";
                }
                System.out.println(printRow);
            }
        }
    }

    /**
     * main()
     */
    public static void main(String[] args) {
        System.out.println("|--Board/main-->");
        int n = 3;
        Board board = new Board(n);
        board.print();
        System.out.println();
        board.setField(new Position(1, 1), 1);
        board.print();
        System.out.println();
        board.doMove(new Position(0, 0), -1);
        board.print();
        System.out.println();
        board.undoMove(new Position(0, 0));
        board.print();
        System.out.println();
        board.doMove(new Position(2, 0), 1);
        board.doMove(new Position(2, 1), 1);
        board.doMove(new Position(2, 2), 1);
        board.doMove(new Position(0, 1), -1);
        board.doMove(new Position(0, 2), -1);
        board.doMove(new Position(1, 2), -1);
        board.print();
        System.out.println("getN(): " + board.getN());
        System.out.println("nFreeFields(): " + board.nFreeFields());
        System.out.println("getField(): " + board.getField(new Position(0, 0)));
        System.out.println("getField(): " + board.getField(new Position(2, 1)));
        System.out.println("getField(): " + board.getField(new Position(1, 2)));
        System.out.println("validMoves(): " + board.validMoves());
        System.out.println("isGameWon():" + board.isGameWon());
        System.out.println();
        board.doMove(new Position(2, 2), -1);
        board.print();
        System.out.println("isGameWon():" + board.isGameWon());
        System.out.println();
        board.doMove(new Position(0, 2), 1);
        board.print();
        System.out.println("isGameWon():" + board.isGameWon());
        System.out.println("<--Board/main--|");
    }
}
