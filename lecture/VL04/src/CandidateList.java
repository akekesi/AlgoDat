import java.util.LinkedList;

/**
 * The class {@code CandidateList} stores possible candidates (digits from 1 to 9) for a
 * {@code cell} of a <b>Sudoku</b> grid. The method {@code cell()} returns the cell to
 * which the candidate list is related.
 *
 * @author Benjamin Blankertz (CC-BY-SA 2023)
 */
public class CandidateList extends LinkedList<Integer> implements Comparable<CandidateList> {

    private final Position cell;

    /**
     * constructor for an empty candidate list labelled with the given cell.
     * @param cell the cell to which the candidate list is related
     */
    public CandidateList(Position cell) {
        super();
        this.cell = cell;
    }

    /**
     * the cell to which the candidate list is related
     * @return the cell
     */
    public Position cell() {
        return cell;
    }

    @Override
    public String toString() {
        return cell.toString() + " -> " + super.toString();
    }

    /**
     * Compare {@code this} with the given {@code object} based on the length of the lists.
     * @param other the object to be compared.
     * @return -1, 0 or 1 depending on this list being shorter, equal or longer than the other list
     */
    public int compareTo(CandidateList other) {
        return Integer.compare(this.size(), other.size());
    }
}
