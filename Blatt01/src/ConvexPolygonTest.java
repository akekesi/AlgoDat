import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ConvexPolygonTest {
    private final double delta = 10e-12;

    @Test
    void testPerimeter() {
        Vector2D a1 = new Vector2D(0, 0);
        Vector2D a2 = new Vector2D(4, 0);
        Vector2D a3 = new Vector2D(4, 3);
        double expectedPerimeter_a = 12;
        ConvexPolygon convexPolygon_a = new ConvexPolygon(new Vector2D[]{a1, a2, a3});
        assertEquals(expectedPerimeter_a, convexPolygon_a.perimeter(), delta, "perimeter() does not work properly");

        Vector2D b1 = new Vector2D(0, 0);
        Vector2D b2 = new Vector2D(4, 0);
        Vector2D b3 = new Vector2D(4, 8);
        Vector2D b4 = new Vector2D(0, 5);
        double expectedPerimeter_b = 22;
        ConvexPolygon convexPolygon_b = new ConvexPolygon(new Vector2D[]{b1, b2, b3, b4});
        assertEquals(expectedPerimeter_b, convexPolygon_b.perimeter(), delta, "perimeter() does not work properly");
    }

    @Test
    void testArea() {
        Vector2D a1 = new Vector2D(0, 0);
        Vector2D a2 = new Vector2D(4, 0);
        Vector2D a3 = new Vector2D(4, 3);
        double expectedArea_a = 6;
        ConvexPolygon convexPolygon_a = new ConvexPolygon(new Vector2D[]{a1, a2, a3});
        assertEquals(expectedArea_a, convexPolygon_a.area(), delta, "area() does not work properly");

        Vector2D b1 = new Vector2D(0, 0);
        Vector2D b2 = new Vector2D(4, 0);
        Vector2D b3 = new Vector2D(4, 8);
        Vector2D b4 = new Vector2D(0, 5);
        double expectedArea_b = 26;
        ConvexPolygon convexPolygon_b = new ConvexPolygon(new Vector2D[]{b1, b2, b3, b4});
        assertEquals(expectedArea_b, convexPolygon_b.area(), delta, "area() does not work properly");
    }

    @Test
    void testToString() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        ConvexPolygon convexPolygon = new ConvexPolygon(new Vector2D[]{a, b, c});
        String expectedToString = "ConvexPolygon(" + Arrays.toString(convexPolygon.vertices) + ")";
        assertEquals(expectedToString, convexPolygon.toString(), "toString() does not return the expected string");
    }

    @Test
    void testSomePolygons() {
    }

    @Test
    void testTotalArea() {
    }

    @Test
    void testWorksheet() {
        String expectedText;
        String actualText;
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        ConvexPolygon convexPolygon = new ConvexPolygon(new Vector2D[]{a, b, c});
        actualText = convexPolygon.toString();
        expectedText = "ConvexPolygon([(0.0, 0.0), (10.0, 0.0), (5.0, 5.0)])";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        Polygon[] somePolygons = ConvexPolygon.somePolygons();
        System.out.println("ONLY PRINT, NO ASSERT:");
        System.out.println(" somePolygons():");
        System.out.println("  3: " + somePolygons[0]);
        System.out.println("  4: " + somePolygons[1]);
        System.out.println("  5: " + somePolygons[2]);
        System.out.println("  6: " + somePolygons[3]);

        System.out.println(" totalArea(somePolygons): " + ConvexPolygon.totalArea(somePolygons));
    }
}
