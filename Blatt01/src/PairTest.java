import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PairTest {

    @Test
    void testGetFirst() {
        int first = 1;
        int second = 2;
        Pair<Integer> pair = new Pair<>(first, second);
        assertSame(first, pair.getFirst(), "getFirst() does not return value used in construction");
    }

    @Test
    void testGetSecond() {
        int first = 1;
        int second = 2;
        Pair<Integer> pair = new Pair<>(first, second);
        assertSame(second, pair.getSecond(), "getSecond() does not return value used in construction");
    }

    @Test
    void testSetFirst() {
        int first1 = 11;
        int first2 = 12;
        int second = 2;
        Pair<Integer> pair = new Pair<>(first1, second);
        pair.setFirst(first2);
        assertSame(first2, pair.getFirst(), "getFirst() does not return value used in setFirst()");
    }

    @Test
    void testSetSecond() {
        int first = 1;
        int second1 = 21;
        int second2 = 22;
        Pair<Integer> pair = new Pair<>(first, second1);
        pair.setSecond(second2);
        assertSame(second2, pair.getSecond(), "getSecond() does not return value used in setSecond()");
    }

    @Test
    void testToString() {
        int first = 1;
        int second = 2;
        Pair<Integer> pair = new Pair<>(first, second);
        String expectedToString = "Pair<" + first + ", " + second + ">";
        assertEquals(expectedToString, pair.toString(), "toString() does not return the expected string");
    }

    @Test
    void testEquals() {
        int first1 = 11;
        int first2 = 11;
        int second1 = 21;
        int second2 = 22;
        Pair<Integer> pair1 = new Pair<>(first1, second1);
        Pair<Integer> pair2 = new Pair<>(first1, second1);
        Pair<Integer> pair3 = new Pair<>(first2, second2);
        assertTrue(pair1.equals(pair1), "equals() does not work properly");
        assertTrue(pair1.equals(pair2), "equals() does not work properly");
        assertFalse(pair1.equals(pair3), "equals() does not work properly");
        assertSame(pair1, pair1, "equals() does not work properly");
        assertNotSame(pair1, pair2, "equals() does not work properly");
        assertNotSame(pair1, pair3, "equals() does not work properly");
    }

    @Test
    void testSwap() {
        int first = 0;
        int second = 0;
        Pair<Integer> pair = new Pair<>(first, second);
        String expected1 = "Pair<" + first + ", " + second + ">";
        String expected2 = "Pair<" + second + ", " + first + ">";
        assertEquals(expected1, pair.toString(), "Either constructor or toString() does not work properly");
        pair.swap();
        assertEquals(expected2, pair.toString(), "swap() does not work properly");
    }

    @Test
    void testWorksheet() {
        int first1 = 1;
        int first2 = 10;
        int second = 2;
        String actualText;
        String expectedText;
        Pair<Integer> pair1 = new Pair<>(first1, second);
        Pair<Integer> pair2 = new Pair<>(first1, second);
        actualText = "Variable pair1 hat den Wert: " + pair1;
        expectedText = "Variable pair1 hat den Wert: Pair<" + first1 + ", " + second + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Variable pair2 hat den Wert: " + pair2;
        expectedText = "Variable pair2 hat den Wert: Pair<" + first1 + ", " + second + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Syntaktische Gleichheit von pair1 und pair2 ist: " + (pair1==pair2);
        expectedText = "Syntaktische Gleichheit von pair1 und pair2 ist: false";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Semantische Gleichheit von pair1 und pair2 ist: " + pair1.equals(pair2);
        expectedText = "Semantische Gleichheit von pair1 und pair2 ist: true";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        Pair<Integer> pair1b = pair1;
        Pair<Integer> pair2b = new Pair<>(pair2);
        pair1.swap();
        pair2.setFirst(first2);
        actualText = "Nach swap() hat Variable pair1 den Wert: " + pair1;
        expectedText = "Nach swap() hat Variable pair1 den Wert: Pair<" + second + ", " + first1 + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Nach setFirst(10) hat Variable pair2 den Wert: " + pair2;
        expectedText =  "Nach setFirst(10) hat Variable pair2 den Wert: Pair<" + first2 + ", " + second + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Die zuvor erstellte Kopie pair1b hat den Wert: " + pair1b;
        expectedText = "Die zuvor erstellte Kopie pair1b hat den Wert: Pair<" + second + ", " + first1 + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Die zuvor erstellte Kopie pair2b hat den Wert: " + pair2b;
        expectedText = "Die zuvor erstellte Kopie pair2b hat den Wert: Pair<" + first1 + ", " + second + ">";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");
    }
}
