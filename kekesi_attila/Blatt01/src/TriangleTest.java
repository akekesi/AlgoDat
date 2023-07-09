import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TriangleTest {
    private final double delta = 10e-12;

    @Test
    void testArea() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(5, 0);
        Vector2D c = new Vector2D(2, 3);
        Triangle triangle = new Triangle(a, b, c);
        double expectedArea = 7.5;
        assertEquals(expectedArea, triangle.area(), delta, "area() does not work properly");
    }

    @Test
    void testToString() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(5, 0);
        Vector2D c = new Vector2D(2, 3);
        Triangle triangle = new Triangle(a, b, c);
        String expectedToString = "Triangle{" + Arrays.toString(triangle.vertices) + "}";
        assertEquals(expectedToString, triangle.toString(), "toString() does not return the expected string");
    }

    @Test
    void testWorksheet() {
        String expectedText;
        String actualText;
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c =  new Vector2D(5, 5);
        Triangle triangle = new Triangle(a, b, c);
        actualText = "Die Fläche des Dreiecks 'triangle' {" + a + ", " + b + ", " + c + "} beträgt " +  String.format("%.1f", triangle.area()) + " LE^2.";
        expectedText = "Die Fläche des Dreiecks 'triangle' {(0.0, 0.0), (10.0, 0.0), (5.0, 5.0)} beträgt 25,0 LE^2.";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        Triangle triangle2 = new Triangle(triangle);
        actualText = "triangle2 ist eine Kopie per Copy-Konstruktor von 'triangle': " + triangle2;
        expectedText = "triangle2 ist eine Kopie per Copy-Konstruktor von 'triangle': Triangle{[(0.0, 0.0), (10.0, 0.0), (5.0, 5.0)]}";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        a.setX(-5);
        actualText = "Eckpunkt 'a', der zur Definition von 'triangle' verwendet wurde, wird geändert.";
        expectedText =  "Eckpunkt 'a', der zur Definition von 'triangle' verwendet wurde, wird geändert.";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Nun ist der Wert von 'triangle2': " + triangle2;
        expectedText = "Nun ist der Wert von 'triangle2': Triangle{[(-5.0, 0.0), (10.0, 0.0), (5.0, 5.0)]}";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");
    }
}