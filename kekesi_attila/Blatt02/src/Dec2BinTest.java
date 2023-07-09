import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Dec2BinTest {

    @Test
    void testAll() {
        Integer[] NList = {0, 1, 2, 3, 4, 10, 11, 17, 50, 99, 100, 128};
        Dec2Bin dec2Bin = new Dec2Bin();
        for (Integer N : NList) {
            dec2Bin.convert(N);
            assertEquals(Integer.toBinaryString(N), dec2Bin.toString(), "either convert() or toString() does not work properly");
            assertEquals(N, dec2Bin.getN(), "getN() does not work properly");
        }
    }

    @Test
    void testWorksheet() {
        Dec2Bin dec2bin = new Dec2Bin();
        dec2bin.convert(50);
        System.out.println("Die Zahl " + dec2bin.getN() + " in Binärdarstellung: " + dec2bin);
        // Do it another time to demonstrate that toString does not erase the binStack.
        System.out.println("Die Zahl " + dec2bin.getN() + " in Binärdarstellung: " + dec2bin);
    }
}
