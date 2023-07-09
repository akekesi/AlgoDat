import java.util.InputMismatchException;


/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
    **/
    public static int alphaBeta(Board board, int player)
    {
        // TODO
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        if (player == 1  || player == -1) {
            return playerMax(board, alpha, beta, player);
        }
        throw new InputMismatchException("Token of player is not valid");
    }

    /**
     * Alpha-Beta search playerMax
     *
     * @param board     Board object
     * @param alpha     alpha
     * @param beta      beta
     * @return          score of game/alpha
     */
    public static int playerMax(Board board, int alpha, int beta, int player) {
        if (board.isGameWon()) {
            return -1 - board.nFreeFields(); // score of previous playerMin round
        }
        if (board.nFreeFields() == 0) {
            return 0;
        }
        int score;
        for (Position position : board.validMoves()) {
            board.doMove(position, player);
            score = playerMin(board, alpha, beta, -1 * player);
            board.undoMove(position);
            if (alpha < score) {
                alpha = score;
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return alpha;
    }

    /**
     * Alpha-Beta search playerMin
     *
     * @param board     Board object
     * @param alpha     alpha
     * @param beta      beta
     * @return          score of game/beta
     */
    public static int playerMin(Board board, int alpha, int beta, int player) {
        if (board.isGameWon()) {
            return 1 + board.nFreeFields(); // score of previous playerMax round
        }
        if (board.nFreeFields() == 0) {
            return 0;
        }
        int score;
        for (Position position : board.validMoves()) {
            board.doMove(position, player);
            score = playerMax(board, alpha, beta, -1 * player);
            board.undoMove(position);
            if (score < beta) {
                beta = score;
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return beta;
    }

    /**
     * Board filled with score of possible moves
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static String[][] evaluateBoard(Board board, int player)
    {
        if (player != 1 && player != -1) {
            throw new InputMismatchException("Token of player is not valid");
        }
        int field;
        int score;
        String token = "";
        String[][] evaluatedBoard = new String[board.getN()][board.getN()];
        for (int y = 0; y < board.getN(); y++) {
            for (int x = 0; x < board.getN(); x++) {
                Position position = new Position(x, y);
                field = board.getField(position);
                if (field == 1) {
                    token = "x";
                }
                if (field == -1) {
                    token = "o";
                }
                if (field == 0) {
                    board.doMove(position, player);
                    score = -1 * alphaBeta(board, -1 * player);
                    token = Integer.toString(score);
                    board.undoMove(position);
                }
                evaluatedBoard[y][x] = token;
            }
        }
        return evaluatedBoard;
    }

    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        // TODO
        String text;
        String token;
        String[][] evaluatedBoard = TicTacToe.evaluateBoard(board, player);
        if (player == 1) {
            token = "x";
        } else if (player == -1) {
            token = "o";
        } else {
            throw new InputMismatchException("Token of player is not valid");
        }
        text = "Evaluated board for player-" + token + ":\n";
        for (int y = 0; y < board.getN(); y++) {
            for (int x = 0; x < board.getN(); x++) {
                text += evaluatedBoard[y][x];
                if (x < board.getN() - 1) {
                    text += " ";
                }
            }
            if (y < board.getN() - 1) {
                text += "\n";
            }
        }
        System.out.println(text);
    }

    /**
     * One fo the best move
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     **/
    public static Position bestMove(Board board, int player) {
        int score = Integer.MIN_VALUE;
        int score_tmp;
        int n = board.getN();
        int positionX = 0;
        int positionY = 0;
        String field;
        String[][] evaluatedBoard = evaluateBoard(board, player);
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                field = evaluatedBoard[y][x];
                if (field != "x" && field != "o") {
                    score_tmp = Integer.parseInt(field);
                    if (score < score_tmp) {
                        score = score_tmp;
                        positionX = x;
                        positionY = y;
                    }
                }
            }
        }
        return new Position(positionX, positionY);
    }


    public static void main(String[] args)
    {
        int n = 3;
        int [][][] xysList = {
                {
                },
                {
                        {0, 0}
                },
                {
                        {0, 1}
                },
                {
                        {2, 2},
                        {1, 0}
                },
                {
                        {2, 2},
                        {1, 0},
                        {2, 0}
                },
                {
                        {2, 2},
                        {1, 0},
                        {2, 0},
                        {2, 1}
                },
                {
                        {2, 2},
                        {1, 0},
                        {2, 0},
                        {2, 1},
                        {1, 1}
                },
                {
                        {2, 2},
                        {1, 0},
                        {2, 0},
                        {2, 1},
                        {1, 1},
                        {0, 0}
                },
                { // after winning game evaluateBoard()/evaluatePossibleMoves() does not work
                        {2, 2},
                        {1, 0},
                        {2, 0},
                        {2, 1},
                        {1, 1},
                        {0, 0},
                        {0, 2}
                }
        };
        int player;
        for (int[][] xys : xysList) {
            player = 1;
            Board board = new Board(n);
            for (int[] xy : xys){
                Position position=new Position(xy[0],xy[1]);
                board.setField(position,player);
                player*=-1;
            }
            board.print();
            TicTacToe.evaluatePossibleMoves(board, player);
            System.out.println();
            String token = "x";
            if (player == -1) {
                token = "o";
            }
            System.out.println("best move for player-" + token + ": " + TicTacToe.bestMove(board, player));
            System.out.println();
        }
    }
}
