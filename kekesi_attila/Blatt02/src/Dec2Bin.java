import java.util.Stack;
/** * A class for constructing a Decimal-to-Binary Number- Converter; * contains a main method for demonstration. */
public class Dec2Bin {

    public Stack<Integer> binStack;  // We make it public to modify it in our tests.
    private int N;

    /**
     * Constructor of an empty object. Use method {@code convert()} to convert a number.
     */
    public Dec2Bin() {
        binStack = new Stack<>();
    }

    /**
     * Returns the number that is converted as {@code int}.
     *
     * @return the converted number
     */
    public int getN() {
        return N;
    }

    /**
     * Converts the given number into binary format, with each digit being represented in a
     * stack of {@code int}.
     *
     * @param N the number that is to be converted.
     */
    public void convert(int N) {
        // TODO implement this method
        this.N = N;
        this.binStack.clear();
        if (N == 0) {
            this.binStack.push(N);
        }
        else {
            while (N > 0) {
                this.binStack.push(N%2);
                N /= 2;
            }
        }
    }

    /**
     * Returns the digits that are stored in {@code binStack} as a string. To is the binary format of the
     * converted number.
     * For testing purpose, we require that the function works also, if the variable {@code binStack} is
     * modified externally.
     *
     * @return a string representation of the number in binary format.
     */
    @Override
    public String toString() {
        // Caution: Stack.toString() does NOT respect stack order. Do not use it.
        // TODO implement this method
        String toString = "";
        for (Integer n : this.binStack) {
            toString = n + toString;
        }
        return toString;
    }
}
