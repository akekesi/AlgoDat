import java.io.FileInputStream;
import java.util.*;

public class RicochetRobots {

    /**
     * Find the shortest move sequence for the given board situation to the goal state,
     * i.e., the designated robot has reached the target field.
     * The task is accomplished by using breadth-first-search. In order to avoid checking
     * the same situations over and over again, each investigated board is put in a hash set.
     *
     * @param board Initial configuration of the game.
     * @return The partial solution containing the shortest move sequence to the target
     */
    public static PartialSolution bfsWithHashing(Board board) {
        /* TODO */
        int hashCode;
        HashMap<Integer, Queue<PartialSolution>> partialSolutionHashMap = new HashMap<>();
        Queue<PartialSolution> partialSolutions = new LinkedList<>();
        PartialSolution partialSolution = new PartialSolution(board);
        while (!partialSolution.isSolution()) {
            for (Move move : partialSolution.validMoves()) {
                PartialSolution partialSolutionNew = new PartialSolution(partialSolution);
                partialSolutionNew.doMove(move);
                hashCode = partialSolutionNew.hashCode();
                if (!partialSolutionHashMap.containsKey(hashCode)) {
                    // key not in hashMap
                    partialSolutions.add(partialSolutionNew);
                    Queue<PartialSolution> partialSolutionValue = new LinkedList<>();
                    partialSolutionValue.add(partialSolutionNew);
                    partialSolutionHashMap.put(hashCode, partialSolutionValue);
                } else {
                    // key in hashMap
                    if (!partialSolutionHashMap.get(hashCode).contains(partialSolutionNew)) {
                        // value not in hashMap
                        partialSolutions.add(partialSolutionNew);
                        partialSolutionHashMap.get(hashCode).add(partialSolutionNew);
                    }
                }
            }
            partialSolution = partialSolutions.remove();
        }
        return partialSolution;
    }

    public static void printBoardSequence(Board board, Iterable<Move> moveSequence) {
        int moveno = 0;
        for (Move move : moveSequence) {
            board.print();
            System.out.println((++moveno) + ". Move: " + move);
            board.doMove(move);
        }
        board.print();
    }

    public static void main(String[] args) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream("samples/rrBoard-sample00.txt"));    // 5
//        System.setIn(new FileInputStream("samples/rrBoard-sample01.txt"));    // 8
//        System.setIn(new FileInputStream("samples/rrBoard-sample02.txt"));    // 14
//        System.setIn(new FileInputStream("samples/rrBoard-sample03.txt"));    // ? 23 ?
//        System.setIn(new FileInputStream("samples/rrBoard-C3-6x4-00.txt"));   // 34
//        System.setIn(new FileInputStream("samples/rrBoard-C3-6x4-01.txt"));   // 26
//        System.setIn(new FileInputStream("samples/rrBoard-C3-6x4-02.txt"));   // 27
//        System.setIn(new FileInputStream("samples/rrBoard-C3-6x4-03.txt"));   // 29
//        System.setIn(new FileInputStream("samples/rrBoard-C3-6x4-04.txt"));   // 30
//        System.setIn(new FileInputStream("samples/rrBoard-D-4x4-00.txt"));    // 18
//        System.setIn(new FileInputStream("samples/rrBoard-D-4x4-01.txt"));    // 18
//        System.setIn(new FileInputStream("samples/rrBoard-D-4x4-02.txt"));    // 20
//        System.setIn(new FileInputStream("samples/rrBoard-D-4x4-03.txt"));    // 18
//        System.setIn(new FileInputStream("samples/rrBoard-D-4x4-04.txt"));    // 18
//        System.setIn(new FileInputStream("samples/rrBoard-S-5x5-00.txt"));    // 8
//        System.setIn(new FileInputStream("samples/rrBoard-S-5x5-01.txt"));    // 9
//        System.setIn(new FileInputStream("samples/rrBoard-S-5x5-02.txt"));    // 9
//        System.setIn(new FileInputStream("samples/rrBoard-S-5x5-03.txt"));    // 8
//        System.setIn(new FileInputStream("samples/rrBoard-S-5x5-04.txt"));    // 9
//        System.setIn(new FileInputStream("samples/rrBoard-T-3x3-00.txt"));    // 4
//        System.setIn(new FileInputStream("samples/rrBoard-T-3x3-01.txt"));    // 4
//        System.setIn(new FileInputStream("samples/rrBoard-T-3x3-02.txt"));    // 4
//        System.setIn(new FileInputStream("samples/rrBoard-T-3x3-03.txt"));    // 4
//        System.setIn(new FileInputStream("samples/rrBoard-T-3x3-04.txt"));    // 4
//        System.setIn(new FileInputStream("samples/rrBoard-XS-4x4-00.txt"));   // 8
//        System.setIn(new FileInputStream("samples/rrBoard-XS-4x4-01.txt"));   // 6
//        System.setIn(new FileInputStream("samples/rrBoard-XS-4x4-02.txt"));   // 7
//        System.setIn(new FileInputStream("samples/rrBoard-XS-4x4-03.txt"));   // 6
//        System.setIn(new FileInputStream("samples/rrBoard-XS-4x4-04.txt"));   // 7
        Board board = new Board(new Scanner(System.in));
        long start = System.nanoTime();
        PartialSolution sol = bfsWithHashing(board);
        long duration1 = (System.nanoTime() - start) / 1000;
        if (sol == null) {
            System.out.println("Board is unsolvable.");
        } else {
            printBoardSequence(board, sol.moveSequence());
            System.out.println("Found solution with " + sol.moveSequence().size() + " moves:\n" + sol);
            System.out.println("Computing time: " + duration1 / 1000 + " ms");
        }
    }
}
