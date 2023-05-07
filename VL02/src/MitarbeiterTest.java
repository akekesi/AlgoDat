import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterTest {

    @Test
    void testGetName() {
        String name = "Otto";
        int nummer = 123;
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, nummer);
        assertEquals(name, mitarbeiter.getName(), "getName() does not work properly");
    }
}
