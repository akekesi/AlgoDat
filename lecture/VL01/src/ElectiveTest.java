import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectiveTest {

    @Test
    public void testGetGraded() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        boolean graded = true;
        Elective algoDatElective = new Elective(attendee, credit, professor, graded);
        assertEquals(graded, algoDatElective.graded, "constructor does not work properly");
        graded = false;
        algoDatElective.graded = graded;
        assertEquals(graded, algoDatElective.graded, "constructor does not work properly");
    }
}
