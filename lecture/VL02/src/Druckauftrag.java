public class Druckauftrag {
    private String auftraggeber;
    private int seitenzahl;

    public Druckauftrag(String auftraggeber, int seitenzahl) {
        this.auftraggeber = auftraggeber;
        this.seitenzahl = seitenzahl;
    }

    public int getSeitenzahl() {
        return this.seitenzahl;
    }

    public void report() {
        System.out.println("Auftraggeber: " + this.auftraggeber);
        System.out.println("Seitenzahl:   " + this.seitenzahl);
    }

    @Override
    public String toString() {
        return "(" + this.auftraggeber + "/" + this.seitenzahl  + ")";
    }
}
