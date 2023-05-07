public class Elective extends Course{

    // attribute
    public boolean graded;

    // constructor
    public Elective(int attendee, int credit, String professor, boolean graded) {
        super(attendee, credit, professor);
        this.graded = graded;
    }
}
