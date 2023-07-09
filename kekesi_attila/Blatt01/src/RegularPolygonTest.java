import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RegularPolygonTest {
    private final double delta = 10e-12;

    @Test
    void testArea() {
        RegularPolygon pentagon = new RegularPolygon(5, 1);
        double expectedArea = 2.377641290737883;
        assertEquals(expectedArea, pentagon.area(), delta, "area() does not work properly");

    }

    @Test
    void testResize() {
        RegularPolygon pentagon1 = new RegularPolygon(5, 1);
        RegularPolygon pentagon2 = new RegularPolygon(pentagon1);
        double expectedArea1 = 2.377641290737883;
        double expectedArea2 = 237.7641290737883;
        assertEquals(expectedArea1, pentagon1.area(), delta, "area() does not work properly");
        assertEquals(expectedArea1, pentagon2.area(), delta, "area() does not work properly");
        pentagon1.resize(10);
        assertEquals(expectedArea2, pentagon1.area(), delta, "Either area() or resize() or constructor does not work properly");
        assertEquals(expectedArea1, pentagon2.area(), delta, "Either area() or resize() or constructor does not work properly");
    }

    @Test
    void testToString() {
        int N = 5;
        double radius1 = 1;
        double radius2 = 10;
        RegularPolygon pentagon1 = new RegularPolygon(N, radius1);
        RegularPolygon pentagon2 = new RegularPolygon(pentagon1);
        pentagon1.resize(radius2);
        String expectedToString1 = "RegularPolygon{N=" + N + ", radius=" + radius1 + "}";
        String expectedToString2 = "RegularPolygon{N=" + N + ", radius=" + radius2 + "}";
        assertEquals(expectedToString2, pentagon1.toString(), "toString() does not return the expected string");
        assertEquals(expectedToString1, pentagon2.toString(), "toString() does not return the expected string");
    }

    @Test
    void testWorksheet() {
        String expectedText;
        String actualText;
        int N = 5;
        double radius1 = 1;
        double radius2 = 10;
        RegularPolygon pentagon = new RegularPolygon(N, radius1);
        actualText = "Der Flächeninhalt des " + pentagon + " beträgt " + pentagon.area() + " LE^2.";
        expectedText = "Der Flächeninhalt des RegularPolygon{N=" + N + ", radius=" + radius1 + "} beträgt 2.377641290737884 LE^2."; // originally 2.377641290737883
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        RegularPolygon otherpentagon = new RegularPolygon(pentagon);
        pentagon.resize(radius2);
        actualText = "Nach Vergrößerung: " + pentagon + " mit Fläche " + pentagon.area() + " LE^2.";
        expectedText = "Nach Vergrößerung: RegularPolygon{N=" + N + ", radius=" + radius2 + "} mit Fläche 237.7641290737884 LE^2.";
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");

        actualText = "Die Kopie: " + otherpentagon + " mit Fläche " + otherpentagon.area() + " LE^2.";
        expectedText = "Die Kopie: RegularPolygon{N=" + N + ", radius=" + radius1 + "} mit Fläche 2.377641290737884 LE^2."; // originally 2.377641290737883
        assertEquals(expectedText, actualText, "test of worksheet does not work properly");
    }
}
