import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testGetAttendee() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(attendee, algoDat.getAttendee(), "getAttendee() does not work properly");
    }

    @Test
    void testGetCredit() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(credit, algoDat.getCredit(), "getCredit() does not work properly");
    }

    @Test
    void testGetProfessor() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(professor, algoDat.getProfessor(), "getProfessor() does not work properly");
    }

    @Test
    void testSetAttendee() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(attendee, algoDat.getAttendee(), "getAttendee() does not work properly");
        attendee = 130;
        algoDat.setAttendee(attendee);
        assertEquals(attendee, algoDat.getAttendee(), "setAttendee() does not work properly");
    }

    @Test
    void testSetCredit() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(credit, algoDat.getCredit(), "getCredit() does not work properly");
        credit = 9;
        algoDat.setCredit(credit);
        assertEquals(credit, algoDat.getCredit(), "setCredit() does not work properly");
    }

    @Test
    void testSetProfessor() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(professor, algoDat.getProfessor(), "getProfessor() does not work properly");
        professor = "XYZ";
        algoDat.setProfessor(professor);
        assertEquals(professor, algoDat.getProfessor(), "getProfessor() does not work properly");
    }

    @Test
    void testSemesterEnd() {
        int attendee = 150;
        int credit = 6;
        String professor = "XY";
        Course algoDat = new Course(attendee, credit, professor);
        assertEquals(attendee, algoDat.getAttendee(), "getAttendee() does not work properly");
        assertEquals(credit, algoDat.getCredit(), "getCredit() does not work properly");
        assertEquals(professor, algoDat.getProfessor(), "getProfessor() does not work properly");
        algoDat.semesterEnd();
        assertEquals(0, algoDat.getAttendee(), "getAttendee() does not work properly");
        assertEquals(credit, algoDat.getCredit(), "getCredit() does not work properly");
        assertEquals(professor, algoDat.getProfessor(), "getProfessor() does not work properly");
    }
}
