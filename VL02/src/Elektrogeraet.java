public abstract class Elektrogeraet {
    int kaufpreis;
    int tageBisPruefdatum = 365;

    public void pruefen() {
        System.out.println("Elektrogeraet pruefen");
        this.tageBisPruefdatum = 365;
    }
}
