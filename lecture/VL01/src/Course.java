public class Course {

    // attribute
    protected int attendee;
    protected int credit;
    protected String professor;

    // constructor
    public Course(int attendee, int credit, String professor) {
        this.attendee = attendee;
        this.credit = credit;
        this.professor = professor;
    }


    // getter
    public int getAttendee() {
        return this.attendee;
    }

    public int getCredit() {
        return credit;
    }

    public String getProfessor() {
        return professor;
    }

    // setter
    public void setAttendee(int attendee) {
        this.attendee = attendee;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    // method
    public void semesterEnd() {
        this.attendee = 0;
    }
}
