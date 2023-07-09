import java.util.Arrays;
import java.util.Stack;


/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    private int[][] matrix;

    public RowOfBowls() {
    }
    
    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values)
    {
        // TODO
        int scoreL;
        int scoreR;
        int n = values.length;
        int[][] matrix = new int[n][n];
        matrix[0] = values.clone();
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                scoreL = matrix[0][j-i] - matrix[i-1][j];
                scoreR = matrix[0][j] - matrix[i-1][j-1];
                matrix[i][j] = Math.max(scoreL, scoreR);
            }
        }
        this.matrix = matrix;
        return matrix[n-1][n-1];
    }

    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values) {
        // TODO
        return maxGainRecursive(values, 0, 1);
    }

    /**
     * subfunction of  public int maxGainRecursive(int[] values)
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @param score score of previous round
     * @param player sign of player (1 or -1)
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values, int score, int player) {
        int lengthValues = values.length;
        if (lengthValues == 1) {
            return score + player * values[0];
        }
        int scoreL = score + player * values[0];
        int scoreR = score + player * values[lengthValues - 1];
        int[] valuesL = Arrays.copyOfRange(values, 1, values.length);
        int[] valuesR = Arrays.copyOfRange(values, 0, values.length - 1);
        scoreL = maxGainRecursive(valuesL, scoreL, -1 * player);
        scoreR = maxGainRecursive(valuesR, scoreR, -1 * player);
        if (player * scoreL < player * scoreR) {
            return scoreR;
        }
        return scoreL;
    }

    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence()
    {
        // TODO
        int tmpL;
        int n = this.matrix.length;
        Stack<Integer> optimalSequence = new Stack<>();
        int[] interval = {0, n - 1};
        while (interval[0] != interval[1]) {
            tmpL = this.matrix[0][interval[0]] - this.matrix[interval[1] - interval[0] - 1][interval[1]];
            if (tmpL == this.matrix[interval[1] - interval[0]][interval[1]]) {
                optimalSequence.push(interval[0]);
                interval[0] += 1;
            } else {
                optimalSequence.push(interval[1]);
                interval[1] -= 1;
            }
        }
        optimalSequence.push(interval[1]);
        return optimalSequence;
    }

    public void print() {
        String text;
        boolean first;
        for (int[] row : this.matrix) {
            text = "";
            first = true;
            for (int element : row) {
                if (!first) {
                    text += " ";
                }
                first = false;
                text += Integer.toString(element);
            }
            System.out.println(text);
        }
    }

    public static void main(String[] args)
    {
        int maxGain;
        int maxGainRecursive;
        Iterable<Integer> optimalSequence;
        int[][] valuesList = {
                {4},
                {4, 7},
                {4, 7, 2},
                {4, 7, 2, 3},
                {3},
                {3, 4},
                {3, 4, 1},
                {3, 4, 1, 2},
                {3, 4, 1, 2, 8},
                {3, 4, 1, 2, 8, 5},
        };
        for (int[] values : valuesList) {
            RowOfBowls rowOfBowls = new RowOfBowls();
            maxGain = rowOfBowls.maxGain(values);
            maxGainRecursive = rowOfBowls.maxGainRecursive(values);
            optimalSequence = rowOfBowls.optimalSequence();
            System.out.println();
            rowOfBowls.print();
            System.out.println("maxGain:          " + maxGain);
            System.out.println("maxGainRecursive: " + maxGainRecursive);
            System.out.println("optimalSequence:  " + optimalSequence);
        }
    }
}
