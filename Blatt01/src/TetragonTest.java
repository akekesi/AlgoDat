import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TetragonTest {

    @Test
    void testConstructor() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(4, 0);
        Vector2D c = new Vector2D(4, 8);
        Vector2D d = new Vector2D(4, 5);
        Tetragon tetragon = new Tetragon(a, b, c, d);
        assertTrue(tetragon instanceof Tetragon, "Constructor does not work properly");

        List<Vector2D> v = Arrays.asList(tetragon.vertices);
        assertTrue(v.contains(a), "vertex a is not in the vertices of the tetragon");
        assertTrue(v.contains(b), "vertex b is not in the vertices of the tetragon");
        assertTrue(v.contains(c), "vertex c is not in the vertices of the tetragon");
        assertTrue(v.contains(d), "vertex d is not in the vertices of the tetragon");
    }

}
