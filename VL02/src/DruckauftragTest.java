import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DruckauftragTest {
    private final double delta = 10e-12;

    @Test
    void testGetSeitenzahl() {
        String auftraggeber = "Otto";
        int seitenzahl = 123;
        Druckauftrag druckauftrag = new Druckauftrag(auftraggeber, seitenzahl);
        assertEquals(seitenzahl, druckauftrag.getSeitenzahl(), delta, "getSeitenzahl() does not work properly");
    }

    @Test
    void testReport() {
        String auftraggeber = "Otto";
        int seitenzahl = 123;
        Druckauftrag druckauftrag = new Druckauftrag(auftraggeber, seitenzahl);
        druckauftrag.report();
        String expectedReport = "Auftraggeber: " + auftraggeber + "\n" + "Seitenzahl: " + seitenzahl;
        assertEquals(expectedReport, druckauftrag.toString(), "getSeitenzahl() does not work properly");
    }
}